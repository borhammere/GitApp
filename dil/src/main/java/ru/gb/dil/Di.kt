package ru.gb.dil

import kotlin.reflect.KClass

object Di {
    private val dependenciesHolder = HashMap<KClass<*>, DependencyFabric<*>>()

    fun <T : Any> get(clazz: KClass<T>): T {
        val dependencyFabric = dependenciesHolder[clazz]
        if (dependencyFabric != null) {
            return dependencyFabric.get() as T
        } else {
            throw IllegalArgumentException("No dep in map")
        }
    }

    fun <T : Any> add(clazz: KClass<T>, dependencyFabric: DependencyFabric<T>) {
        dependenciesHolder[clazz] = dependencyFabric
    }

    inline fun <reified T : Any> add(dependencyFabric: DependencyFabric<T>) {
        add(T::class, dependencyFabric)
    }


}

inline fun <reified T : Any> get(): T {
    return Di.get(T::class)
}

inline fun <reified T : Any> inject() = lazy {
    get<T>()
}

abstract class DependencyFabric<T : Any>(protected val creator: () -> Any) {
    abstract fun get(): Any
}

class Singleton<T : Any>(creator: () -> Any) : DependencyFabric<T>(creator) {
    private val dependency: Any by lazy { creator.invoke() }

    override fun get(): Any = dependency
}

class Fabric<T : Any>(creator: () -> Any) : DependencyFabric<T>(creator) {
    override fun get(): Any = creator()
}

class Module(private val block: Module.() -> Unit) {
    fun install() {
        block()
    }

    inline fun <reified T : Any> singleton(noinline creator: () -> T) {
        Di.add(Singleton<T>(creator))
    }

    inline fun <reified T : Any> fabric(noinline creator: () -> T) {
        Di.add(Fabric<T>(creator))
    }
}
