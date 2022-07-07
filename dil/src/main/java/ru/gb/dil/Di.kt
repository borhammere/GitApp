package ru.gb.dil

import kotlin.reflect.KClass

object Di {
    private val dependenciesHolder = HashMap<KClass<*>, DependencyFabric>()

    fun <T : Any> get(clazz: KClass<T>): T {
        val dependencyFabric = dependenciesHolder[clazz]
        if (dependencyFabric != null) {
            return dependencyFabric.get() as T
        } else {
            throw IllegalArgumentException("No dep in map")
        }
    }

    fun <T : Any> add(clazz: KClass<T>, dependencyFabric: DependencyFabric) {
        dependenciesHolder[clazz] = dependencyFabric
    }

}

inline fun <reified T : Any> get(): T {
    return Di.get(T::class)
}

inline fun <reified T : Any> inject() = lazy {
    get<T>()
}

abstract class DependencyFabric(protected val creator: () -> Any) {
    abstract fun get(): Any
}

class Singleton(creator: () -> Any) : DependencyFabric(creator) {
    private val dependency: Any by lazy { creator.invoke() }

    override fun get(): Any = dependency
}

class Fabric(creator: () -> Any) : DependencyFabric(creator) {
    override fun get(): Any = creator()
}