package com.zsqw123.catreflect

import kotlin.reflect.KClass

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 3:39 下午
 */
/**
 * 形如 method("ss")[String::class,Int::Class](1,2) 这样的调用
 */
interface TypedArg<T> {
    /** 定义参数类型 */
    operator fun get(vararg types: Class<*>): T
    operator fun get(vararg types: KClass<*>): T = get(*Array(types.size) { types[it].java })

    /** 调用 */
    operator fun invoke(vararg vars: Any?): Any?
}