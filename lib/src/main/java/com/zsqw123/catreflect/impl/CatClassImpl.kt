package com.zsqw123.catreflect.impl

import com.zsqw123.catreflect.CatClass
import com.zsqw123.catreflect.CatMethod
import com.zsqw123.catreflect.CatValue
import com.zsqw123.catreflect.util.constructorMatch
import com.zsqw123.catreflect.util.getArgsTypes
import java.lang.reflect.Constructor

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 8:44 上午
 */
internal class CatClassImpl(private val innerClazz: Class<*>, safe: Boolean = false) : CatClass(innerClazz, safe) {
    private var innerTypes: Array<Class<*>>? = null

    override fun prop(propName: String): CatValue = CatValue.from(this, propName, false)
    override fun safeProp(propName: String): CatValue = CatValue.from(this, propName, true)

    override fun method(methodName: String): CatMethod = CatMethod.from(this, methodName, false)
    override fun safeMethod(methodName: String): CatMethod = CatMethod.from(this, methodName, true)

    /** 构造函数的参数类型 */
    override fun get(vararg types: Class<*>): CatClass = apply { innerTypes = arrayOf(*types) }

    /** 调用构造方法并保存到 instance，如果未申明 declared，则如果在 constructors 中找不到，会自动重定向到 declaredConstructors */
    override fun invoke(vararg vars: Any?) = apply {
        val types = innerTypes ?: getArgsTypes(*vars)
        val constructor: Constructor<*> = constructorMatch(innerClazz, types, safe)
        instance = constructor.newInstance(*vars)
    }
}