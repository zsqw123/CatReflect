package com.zsqw123.catreflect

import com.zsqw123.catreflect.util.getOrCreateValue

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

    companion object {
        fun <T> from(clazz: CatClass, valueName: String, safe: Boolean = false): CatValue<T> =
            getOrCreateValue(clazz, valueName, safe)
    }
}