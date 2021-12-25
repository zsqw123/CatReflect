package com.zsqw123.catreflect.util

import com.zsqw123.catreflect.CatClass
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.reflect.KClass

/**
 * Author zsqw123
 * Create by zsqw123
 * Date 2021/12/23 8:46 上午
 */
internal fun logw(chn: String, eng: String = "") {
    Logger.getLogger("CatReflect").log(Level.WARNING, "$chn $eng")
}

internal fun getArgsTypes(vararg vars: Any?): Array<Class<*>> {
    val types: Array<Class<out Any>> = if (vars.isEmpty()) arrayOf()
    else Array(vars.size) {
        val arg = vars[it] ?: throw CatReflectException(
            "空的参数需要通过类似于这样的方式: [Type1::class,Type2::class] 指定类型.",
            "Null parameters need to be typed explicitly by this way: [Type1::class,Type2::class]."
        )
        val kClass = arg::class
        // 默认获取基本类型
        kClass.javaPrimitiveType ?: kClass.java
    }
    return types
}

internal fun Class<*>.canReplaceTo(replaceTo: Class<*>): Boolean {
    // superclass is ok
    if (replaceTo.isAssignableFrom(this)) return true
    val aPrimitiveType = this.kotlin.javaPrimitiveType ?: return false
    val bPrimitiveType = replaceTo.kotlin.javaPrimitiveType ?: return false
    return aPrimitiveType == bPrimitiveType
}

val <reified T : Class<*>> T.new inline get() = CatClass.from(this)
val <reified T : KClass<*>> T.new inline get() = CatClass.from(this.java)
val <reified T : Any> T.reflect inline get() = CatClass.from(this::class.java).instance(this)
