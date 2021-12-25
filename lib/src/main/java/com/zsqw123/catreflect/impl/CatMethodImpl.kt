package com.zsqw123.catreflect.impl

import com.zsqw123.catreflect.CatClass
import com.zsqw123.catreflect.CatMethod
import com.zsqw123.catreflect.util.getArgsTypes
import com.zsqw123.catreflect.util.methodMatch

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 9:37 上午
 */
internal class CatMethodImpl(
    clazz: CatClass, methodName: String, safe: Boolean = false
) : CatMethod(clazz, methodName, safe) {
    private var innerTypes: Array<Class<*>>? = null

    /** 方法参数类型，也可以不设置，会自动推断 */
    override fun get(vararg types: Class<*>) = apply { innerTypes = arrayOf(*types) }

    /** 调用方法，如果未申明 declared，则如果在 methods 中找不到，会自动重定向到 declaredMethod */
    override fun invoke(vararg vars: Any?): Any? {
        val types: Array<Class<*>> = innerTypes ?: getArgsTypes(*vars)
        val method = methodMatch(clazz.getClazz(), methodName, types, safe)
        return method.invoke(clazz.instance(), *vars)
    }
}