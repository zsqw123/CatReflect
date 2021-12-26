package com.zsqw123.catreflect

import com.zsqw123.catreflect.impl.CatMethodImpl

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 8:40 上午
 */
abstract class CatMethod(
    protected val clazz: CatClass, protected val methodName: String, protected var safe: Boolean = false
) : TypedArg<CatMethod> {
    companion object {
        @JvmStatic
        @JvmOverloads
        fun from(clazz: CatClass, methodName: String, safe: Boolean = false): CatMethod {
            return CatMethodImpl(clazz, methodName, safe)
        }
    }
}