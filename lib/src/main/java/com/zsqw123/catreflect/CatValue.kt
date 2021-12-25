package com.zsqw123.catreflect

import com.zsqw123.catreflect.impl.CatValueImpl


/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 8:41 上午
 */
abstract class CatValue(
    protected val clazz: CatClass, protected val valueName: String, protected val safe: Boolean = false
) {
    abstract fun <T> get(): T
    abstract fun <T> set(value: T)
    companion object {
        fun from(clazz: CatClass, valueName: String, safe: Boolean = false): CatValue {
            return CatValueImpl(clazz, valueName, safe)
        }
    }
}