package com.zsqw123.catreflect.util

import java.lang.reflect.Constructor
import java.util.*

internal val constructorCache: MutableMap<ConstructorDescriptor, Constructor<*>> = WeakHashMap()

internal class ConstructorDescriptor(
    var clazz: Class<*>,
    var parameterType: Array<Class<*>>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConstructorDescriptor

        if (clazz != other.clazz) return false
        if (!parameterType.contentEquals(other.parameterType)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = clazz.hashCode()
        result = 31 * result + parameterType.contentHashCode()
        return result
    }
}

internal fun constructorMatch(
    clazz: Class<*>,
    parameterType: Array<Class<*>>,
    safe: Boolean = false,
): Constructor<*> {
    // fast cache
    val cd = ConstructorDescriptor(clazz, parameterType)
    val cache = constructorCache[cd]
    if (cache != null) return cache

    // fast match use Constructor
    try {
        val constructor = clazz.getConstructor(*parameterType)
        constructor.isAccessible = true
        constructorCache[cd] = constructor
        return constructor
    } catch (_: NoSuchMethodException) {  /* SWALLOW */
    }

    // fast match use DeclaredConstructor
    if (!safe) try {
        val constructor = clazz.getDeclaredConstructor(*parameterType)
        constructor.isAccessible = true
        constructorCache[cd] = constructor
        return constructor
    } catch (_: NoSuchMethodException) {  /* SWALLOW */
    }

    // search all methods
    var allConstructors = clazz.constructors
    if (!safe) allConstructors += clazz.declaredConstructors
    val parameterCount = parameterType.size
    var bestRes: Constructor<*>? = null
    var bestCost = Int.MAX_VALUE
    for (constructor in allConstructors) {
        val types = constructor.parameterTypes
        if (types.size != parameterCount) continue
        var cost = 0
        var failed = false
        for (i in types.indices) {
            if (types[i] == parameterType[i]) continue
            if (types[i].canReplaceTo(parameterType[i])) {
                cost += 1
                continue
            }
            failed = true
            break
        }
        if (failed) continue
        if (cost < bestCost) {
            bestRes = constructor
            bestCost = cost
        }
    }
    if (bestRes != null) {
        bestRes.isAccessible = true
        constructorCache[cd] = bestRes
        return bestRes
    }
    throw NoSuchMethodException("No contructor: $clazz ${parameterType.toList()}")
}