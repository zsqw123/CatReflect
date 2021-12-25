package com.zsqw123.catreflect.util

import com.zsqw123.catreflect.CatClass
import com.zsqw123.catreflect.CatValue
import com.zsqw123.catreflect.impl.CatValueImpl
import java.util.*

internal class ValueDescriptor(var clazz: CatClass, var valueName: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ValueDescriptor

        if (clazz != other.clazz) return false
        if (valueName != other.valueName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = clazz.hashCode()
        result = 31 * result + valueName.hashCode()
        return result
    }
}

internal fun <T> getOrCreateValue(clazz: CatClass, valueName: String, safe: Boolean = false): CatValue<T> {
    val vd = ValueDescriptor(clazz, valueName)
    @Suppress("UNCHECKED_CAST")
    if (valueCache.containsKey(vd)) return (valueCache[vd]) as CatValue<T>
    val impl = CatValueImpl.create<T>(vd, safe)
    valueCache[vd] = impl
    return impl
}

internal val valueCache: MutableMap<ValueDescriptor, CatValue<*>> = WeakHashMap()