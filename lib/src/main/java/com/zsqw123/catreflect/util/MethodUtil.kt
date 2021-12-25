package com.zsqw123.catreflect.util

import java.lang.reflect.Method
import java.util.*

private val methodCache: MutableMap<MethodDescriptor, Method> = WeakHashMap()

internal class MethodDescriptor(
    var clazz: Class<*>,
    var methodName: String,
    var parameterType: Array<Class<*>>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MethodDescriptor

        if (clazz != other.clazz) return false
        if (methodName != other.methodName) return false
        if (!parameterType.contentEquals(other.parameterType)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = clazz.hashCode()
        result = 31 * result + methodName.hashCode()
        result = 31 * result + parameterType.contentHashCode()
        return result
    }
}

internal fun methodMatch(
    clazz: Class<*>,
    methodName: String,
    parameterType: Array<Class<*>>,
    safe: Boolean = false,
): Method {
    // fast cache
    val md = MethodDescriptor(clazz, methodName, parameterType)
    val cache = methodCache[md]
    if (cache != null) return cache

    // fast match use Method
    try {
        val method = clazz.getMethod(methodName, *parameterType)
        method.isAccessible = true
        methodCache[md] = method
        return method
    } catch (_: NoSuchMethodException) {  /* SWALLOW */
    }

    // fast match use DeclaredMethod
    if (!safe) try {
        val method = clazz.getDeclaredMethod(methodName, *parameterType)
        method.isAccessible = true
        methodCache[md] = method
        return method
    } catch (_: NoSuchMethodException) {  /* SWALLOW */
    }

    // search all methods
    var allMethods = clazz.methods
    if (!safe) allMethods += clazz.declaredMethods
    val parameterCount = parameterType.size
    var bestRes: Method? = null
    var bestCost = Int.MAX_VALUE
    for (method in allMethods) {
        if (method.name != md.methodName) continue
        val types = method.parameterTypes
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
            bestRes = method
            bestCost = cost
        }
    }
    if (bestRes != null) {
        bestRes.isAccessible = true
        methodCache[md] = bestRes
        return bestRes
    }
    throw NoSuchMethodException("$clazz#$methodName ${parameterType.toList()}")
}