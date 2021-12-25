package com.zsqw123.catreflect

import com.zsqw123.catreflect.impl.CatClassImpl

/**
 * 有两种方式实例化，一种是通过 instance(ins)/fromInstance(obj),
 * 另一种方式是通过 TypedArg, 使用构造函数进行构造:
 */
abstract class CatClass(private val innerClazz: Class<*>, protected var safe: Boolean = false) : TypedArg<CatClass> {

    /** 通过实例进行实例化 */
    fun instance(ins: Any?): CatClass = apply { instance = ins }
    fun instance() = instance
    protected var instance: Any? = null

    /** 获取包裹的 class */
    fun getClazz(): Class<*> = innerClazz

    /** 构造函数 */
    abstract override fun invoke(vararg vars: Any?): CatClass

    abstract fun prop(propName: String): CatValue
    abstract fun safeProp(propName: String): CatValue // no DeclaredField
    abstract fun method(methodName: String): CatMethod
    abstract fun safeMethod(methodName: String): CatMethod // no DeclaredMethod

    companion object {
        fun from(clazz: Class<*>, safe: Boolean = false): CatClass = CatClassImpl(clazz, safe)
        fun from(className: String, safe: Boolean = false): CatClass = from(Class.forName(className), safe)
        inline fun <reified C : Any> fromInstance(obj: C, safe: Boolean = false): CatClass = from(C::class.java, safe).instance(obj)
    }
}