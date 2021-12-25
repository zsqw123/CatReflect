import com.zsqw123.catreflect.util.new
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    // 静态类 / create a static class
    val static = Sample::class.new
    // 单参实例化 / create a instance use constructor
    val instance = Sample::class.new(1)
    // 访问双参静态函数 / call a static function with 2 parameters
    static.method("staticPrivate")(1, 2)
    // 访问单参成员函数 / call a member function with 1 parameter
    instance.method("callPrivate")(1)
    // 获取及修改静态属性 / get and modify static property
    val b = static.prop<Int>("b")
    log(b.get())
    b.set(2)
    log(b.get())
    // 获取及修改成员属性 / get and modify member property
    val a = instance.prop<Int>("a")
    log(a.get())
    a.set(3)
    log(a.get())
    // 属性委托 / Property Delegation
    var c: Int by instance.prop("a")
    log(c)
    c = 5
    log(a.get())

}

fun log(a: Any? = null) {
    println("[${SimpleDateFormat("hh:mm:ss:SSS").format(Date())}]: $a")
}