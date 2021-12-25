# CatReflect

[![Maven Central](https://img.shields.io/maven-central/v/io.github.zsqw123/cat-reflect)](https://search.maven.org/artifact/io.github.zsqw123/cat-reflect)

这是一个可以极大简化 Kotlin 反射 Java 的复杂操作的工具  
This is a tool that greatly simplifies the complex operations of Kotlin Reflection Java.

## Quick View

如果你需要大量操作反射，那么下面的方法可以帮你节省很多时间：  
If you need to use reflection a lot, then the following methods can save you a lot of time

1. 获得类 / Get Class
```kotlin
// 静态类 / create a static class
val static = Sample::class.new
// 单参实例化 / create a instance use constructor
val instance = Sample::class.new(1)
```
2. 访问函数 / Invoke Method
```kotlin
// 访问双参静态函数 / call a static function with 2 parameters
static.method("staticPrivate")(1, 2)
// 访问单参成员函数 / call a member function with 1 parameter
instance.method("callPrivate")(1)
```
3. 访问属性 / Access Properties
```kotlin
// 获取及修改静态属性 / get and modify static property
val b = static.prop("b")
println(b.get<Int>())
b.set(2)
println(b.get<Int>())
// 获取及修改成员属性 / get and modify member property
val a = instance.prop("a")
println(a.get<Int>())
a.set(3)
println(a.get<Int>())
```