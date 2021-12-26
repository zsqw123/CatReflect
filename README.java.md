# CatReflect Java

Example: [Code example](/CatReflectExample/src/main/java/Sample.java)#main

## Class

```kotlin
CatClass.from("Sample") // Only static members can be accessed
CatClass.from(Sample.class).constructor(1) // Instances are created, both static and non-static members are accessible
```

## Method

```kotlin
catclass.method("staticPrivate").invoke(1,2) // call static function
catclass.constructor(1).method("staticPrivate").invoke(1,2)
catclass.constructor(1).method("callPrivate").invoke(3) // call member function
```

## Value

```kotlin
catclass.prop("b").set(1)
catclass.prop("b").get() // 1
```

More? see: [README](README.md)
