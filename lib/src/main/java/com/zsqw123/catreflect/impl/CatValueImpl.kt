package com.zsqw123.catreflect.impl

import com.zsqw123.catreflect.CatClass
import com.zsqw123.catreflect.CatValue
import java.lang.reflect.Field

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 4:14 下午
 */
class CatValueImpl(
    clazz: CatClass, valueName: String, safe: Boolean = false
) : CatValue(clazz, valueName, safe) {
    private val field: Field by lazy {
        val clz = clazz.getClazz()
        return@lazy try {
            val f = clz.getField(valueName)
            f.isAccessible = true
            f
        } catch (_: NoSuchFieldException) {
            val f = clz.getDeclaredField(valueName)
            f.isAccessible = true
            f
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(): T {
        return field.get(clazz.instance()) as T
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> set(value: T) {
        field.set(clazz.instance(), value)
    }
}