### Why Java Forbids Generic Exceptions

In Java, you cannot create, catch, or throw an object of a generic type parameter. This is a direct consequence of Type Erasure.

#### 1. The Forbidden Code

If you try to write the following, the compiler will stop you:
```java
public class ExceptionHandler<T extends Exception> {
    public void handle() {
        try {
        // ... logic ...
        } catch (T e) { // COMPILE ERROR: Cannot catch type parameter
            e.printStackTrace();
        }   
    }
}
```


#### 2. The Technical Reason: Runtime Identity

The JVM handles exceptions at runtime. When an exception is thrown, the JVM looks at the catch blocks one by one to see which one "matches" the type of the exception.

The Erasure Problem:

Because of Type Erasure, the type T disappears. If the compiler allowed catch (T e), the bytecode would look like this:
```java
// What the JVM would see after Erasure:
try {
// ...
} catch (Exception e) {
// ...
}
```

The JVM would be "blind" to whether it was supposed to catch a specific T or just a general Exception.

#### 3. The Workaround: Throwing Wrapped Exceptions

Since you cannot catch T, the standard "workaround" is to catch a broad exception and check the type manually, or use a wrapper.

```java
public <T extends Exception> void process(Class<T> exceptionType) throws T {
    try {
    // Some risky logic
    } catch (Exception e) {
        if (exceptionType.isInstance(e)) {
            throw exceptionType.cast(e); // Rethrow as the specific type T
        }
        throw new RuntimeException(e);
    }
}

```


#### 4. Summary

1. Generic types are for the compiler.

2. Exceptions are for the JVM at runtime.

3. Because the compiler erases the types, the JVM would be "blind" to which generic exception it was supposed to catch.