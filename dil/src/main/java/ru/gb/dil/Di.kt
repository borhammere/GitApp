package ru.gb.dil

import kotlin.reflect.KClass

class Di {
    private val dependenciesHolder = HashMap<KClass<*>, Any>()

    fun <T : Any> get(clazz: KClass<T>): T {
        if (dependenciesHolder.containsKey(clazz)) {
            return dependenciesHolder[clazz] as T
        } else {
            throw IllegalArgumentException("No dep in map")
        }
    }

    fun <T : Any> add(clazz: KClass<T>, dependency: T) {
        dependenciesHolder[clazz] = dependency
    }

    fun <T : Any> add(dependency: T) {
        dependenciesHolder[dependency::class] = dependency
    }

}