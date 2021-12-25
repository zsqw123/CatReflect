package com.zsqw123.catreflect

import com.zsqw123.catreflect.util.getOrCreateValue
import kotlin.reflect.KProperty

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 8:41 上午
 */
abstract class CatValue<T>(
    protected val clazz: CatClass, protected val valueName: String, protected val safe: Boolean = false
) {
    abstract fun get(): T
    abstract fun set(value: T)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = get()
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = set(value)

    companion object {
        fun <T> from(clazz: CatClass, valueName: String, safe: Boolean = false): CatValue<T> =
            getOrCreateValue(clazz, valueName, safe)
    }
}