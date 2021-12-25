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

## Usage

Gradle: [![Maven Central](https://img.shields.io/maven-central/v/io.github.zsqw123/cat-reflect)](https://search.maven.org/artifact/io.github.zsqw123/cat-reflect)

```groovy
repositories {
    mavenCentral()
}
implementation("io.github.zsqw123:cat-reflect:$version")
```

### Class

反射的第一步是需要获取 `CatClass`，它是 `java.lang.Class` 的包装类，可以通过 Class/KClass 来创建它，也可以使用任意一个已初始化的对象来创建 `CatClass` 对象并绑定实例:  
The first step in reflection is to get `CatClass`, which is a wrapper class for `java.lang.Class` and can be created by Class/KClass or by using any of the initialized objects to
create a `CatClass` object and bind the instance:

```kotlin
val Class<*>.new // 从 Class 获取 CatClass / create from Class<*>
val KClass<*>.new // 从 KClass 获取 CatClass / create from KClass<*>
val Any.reflect // 从已初始化对象获取 CatClass 并绑定实例 / using initialized object to create a CatClass and bind this object

CatClass.from(Class<*>) // `Class<*>.new` Complete call
CatClass.from("class name") // create class by class name
CatClass.fromInstance(Any) // `Any.reflect` Complete call
```

通过上面的方法就可以得到静态类，但如果要使用自定义的构造函数，那么就需要用到`构造器`，构造器的参数类型将被`自动推断`，或者`手动指定`：  
The above method will give you a static class, but if you want to use a custom constructor, then you will need to use a constructor whose parameter type will
be `automatically inferred`, or `manually specified` as follows:

```kotlin
Class<*>.new(1, 2, 3) // Auto type
Class<*>.new[Int::class, Int::class](1, 2) // Manual type
```

在默认情况下，`私有构造函数`也是允许被调用的，如果这不符合您的预期，请使用如下方式来避免反射访问私有构造函数  
By default, `private constructors` are also allowed to be called, but if this is not what you expect, to avoid reflection access to private constructors as follows:

```kotlin
CatClass.from(Class<*>, true)[Int::class, Int::class](1, 2) // Safe manual type
CatClass.from("class name", true) // safe create class by class name
CatClass.fromInstance(Any, true) // `Any.reflect` Complete call
```

### Method

通过 `catClass.method("method name")` 获得 `CatMethod` / Get `CatMethod` by `catClass.method("method name")`:

```kotlin
Class<*>.new.method("awa") // static function
Class<*>.new(1, 2).method("awa") // member function
Class<*>.new.safeMethod("awa") // 使用 safeMethod 避免访问私有函数 / avoid call private function by safeMethod
```

调用函数可以通过 `invoke()` 方法调用，也可以省略它，就像普通函数调用一样，函数参数的类型将被`自动推断`，或者也可以使用`手动指定`:  
Calling function can be called by the `invoke()` method, or it can be omitted, just like a normal function call, and the type of the function argument will
be `automatically inferred`, or it can be manually specified using `manually specified`:

```kotlin
method("awa").invoke() // no parameter invoke
method("awa")() // no parameter invoke
method("awa")(1, 2) // Auto type
method("awa")[Int::class, Int::class](1, 2) // Manual type
```

### Value

通过 `catClass.prop("value name")` 获得 `CatValue` / Get `CatValue` by `catClass.prop("value name")`:

```kotlin
Class<*>.new.prop("awa") // static variable
Class<*>.new(1, 2).prop("awa") // member variable
Class<*>.new.safeProp("awa") // 使用 safeProp 避免访问变量 / avoid access private variable by safeProp
```

得到 CatValue 之后可以通过 `get/set` 方法来操作变量:

```kotlin
prop("b").set(2)
prop("b").get<Int>()
```

## LICENSE

[Apache License 2.0](LICENSE)