import com.zsqw123.catreflect.util.new

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
    println(b.get())
    b.set(2)
    println(b.get())
    // 获取及修改成员属性 / get and modify member property
    val a = instance.prop<Int>("a")
    println(a.get())
    a.set(3)
    println(a.get())
}