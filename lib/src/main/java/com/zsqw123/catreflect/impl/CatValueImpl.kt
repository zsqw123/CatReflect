package com.zsqw123.catreflect.impl

import com.zsqw123.catreflect.CatClass
import com.zsqw123.catreflect.CatValue
import com.zsqw123.catreflect.util.ValueDescriptor
import java.lang.reflect.Field

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 4:14 下午
 */
class CatValueImpl<T>(
    clazz: CatClass, valueName: String, safe: Boolean = false
) : CatValue<T>(clazz, valueName, safe) {
    private val field: Field by lazy {
        val clz = clazz.getClazz()
        var f: Field? = null
        kotlin.runCatching {
            f = clz.getField(valueName)
        }
        if (f == null && !safe) f = try {
            clz.getDeclaredField(valueName)
        } catch (e: Exception) {
            findParentField(clz)
        }
        f?.isAccessible = true
        f ?: nsfe()
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(): T {
        return field.get(clazz.instance()) as T
    }

    override fun set(value: T) {
        field.set(clazz.instance(), value)
    }

    private fun findParentField(clz: Class<*>): Field {
        val superClass = clz.superclass ?: nsfe()
        return try {
            superClass.getDeclaredField(valueName)
        } catch (_: NoSuchFieldException) {
            if (superClass.superclass == null) nsfe()
            findParentField(superClass)
        }
    }

    private fun nsfe(): Nothing = throw NoSuchFieldException("${clazz.getClazz()} $valueName")

    companion object {
        internal fun <T> create(vd: ValueDescriptor, safe: Boolean = false): CatValue<T> = CatValueImpl(vd.clazz, vd.valueName, safe)
    }
}