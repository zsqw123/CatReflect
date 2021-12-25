# CatReflect

这是一个可以极大简化 Kotlin 反射 Java 的复杂操作的工具 This is a tool that greatly simplifies the complex operations of Kotlin Reflection Java.

## Quick View

如果你需要大量操作反射，那么下面的方法可以帮你节省很多时间：

```kotlin
// 静态类
// create a static class
val static = Sample::class.new
// 单参实例化
// create a instance use constructor
val instance = Sample::class.new(1)
// 访问双参静态函数
// call a static function with 2 parameters
static.method("staticPrivate")(1, 2)
// 访问单参成员函数
// call a instance's function with 1 parameter
instance.method("callPrivate")(1)
// 获取及修改静态属性
// get and modify static property
val b: Int = static.prop("b").get()
println(b)
static.prop("b").set(2)
println(static.prop("b").get<Int>())
// 获取及修改成员属性
val a: Int = instance.prop("a").get()
println(a)
instance.prop("a").set(3)
println(static.prop("b").get<Int>())
```