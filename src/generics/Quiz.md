Java Generics Mastery Quiz

Test your knowledge of Java Generics syntax, intuition, and the underlying mechanics of Type Erasure. Choose the best answer for each question and click the detail block to reveal the correct rationale.

1. Class Definitions

    You are defining a new class called Storage that can hold any type. Which is the correct syntax for the class "Label"?

        A) public class Storage<T>

        B) public <T> class Storage

        C) public class <T> Storage

        D) public class Storage(T)

    <details>
        <summary><b>Click to see the answer</b></summary>

        Correct Answer: A

        Rationale: In a class definition, the type parameter (the label) follows the class name to define the scope for the entire class. Generics use angle brackets <>, not parentheses.

    </details>

---
2. Static Method "Permission Slips"

  You have a non-generic utility class. You want to write a static method that takes a list of any type and returns the first element. What is missing in this signature?
  public static T getFirst(List<T> list)

    A) The "Permission Slip" <T> before the return type.
    
    B) A ? wildcard in the return type.
    
    C) The extends Object bound.
    
    D) The static keyword should follow the generic type.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Static methods cannot see class-level type parameters; they must declare their own using <T> before the return type so the compiler knows T is a placeholder for this specific method call.

</details>

---
3. Upper Bounds

You want a method to only accept lists of types that are Number or its subclasses. Which syntax correctly applies this Upper Bound?

    A) void process(List<? extends Number> list)
    
    B) void process(List<? super Number> list)
    
    C) void process(List<T extends Number> list)
    
    D) void process(List<? implements Number> list)

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: The extends keyword is used for upper bounds (going "down" the hierarchy). implements is never used in generic bounds, even for interfaces.

</details>

---
4. Multiple Bounds

In a method definition, you need a type T that must extend Number AND implement Comparable. What is the correct syntax for these multiple bounds?

    A) <T extends Number & Comparable<T>>
    
    B) <T extends Number, Comparable<T>>
    
    C) <T extends Number | Comparable<T>>
    
    D) <T extends Number and Comparable<T>>

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Multiple bounds in Java are joined by the ampersand & symbol. A comma is used to separate different type parameters (like <K, V>), not multiple requirements for one parameter.

</details>

---
5. The Diamond Operator

What is the primary purpose of the "Diamond Operator" (<>) introduced in Java 7?

    A) To allow the compiler to infer type arguments for constructors.
    
    B) To define a wildcard that can both read and write.
    
    C) To bypass type erasure at runtime.
    
    D) To create an array of a generic type.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: It reduces verbosity by letting the compiler look at the variable declaration (left side) to determine the constructor's types (right side), known as Type Inference.

</details>

---
6. PECS: The Producer Rule

According to the PECS rule (Producer Extends, Consumer Super), which operation is ILLEGAL on a List<? extends Number>?

    A) list.add(10);
    
    B) Number n = list.get(0);
    
    C) int size = list.size();
    
    D) list.clear();

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: A list with an upper bound (? extends) is a Producer; you can read from it, but you cannot write to it because the compiler doesn't know if the actual list is a List<Integer>, List<Double>, etc.

</details>

---
7. PECS: The Consumer Rule

You have a List<? super Integer>. What is the most specific type you can guarantee when calling list.get(0)?

    A) Object
    
    B) Integer
    
    C) Number
    
    D) ?

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Because the list could be a List<Integer>, List<Number>, or List<Object>, the only common ancestor we can guarantee for reading is Object.

</details>

---
8. Runtime Erasure

Which of these is a direct consequence of Type Erasure at runtime?

    A) List<String> and List<Integer> share the same class file at runtime.
    
    B) The JVM performs faster type checks for generic code.
    
    C) Generic types are converted to primitive types like int.
    
    D) Bridge methods are removed to save memory.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: The JVM is "blind" to generics. It sees both as the raw List class because type information is stripped during compilation.

</details>

---
9. Bridge Methods

Why does the compiler generate "Bridge Methods" during Type Erasure?

    A) To preserve polymorphism when a class implements a generic interface.
    
    B) To allow generic types to be used in static contexts.
    
    C) To convert generic collections into arrays automatically.
    
    D) To prevent Heap Pollution.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: It ensures that method calls to the erased Object version of a method are correctly routed to the "Typed" version in your specific subclass.

</details>

---
10. Generic Arrays

Which statement about generic arrays is TRUE?

    A) Creating new T[10] is illegal because arrays must know their component type at runtime.
    
    B) Generic arrays can be created if T extends Number.
    
    C) You can create a generic array by using the diamond operator.
    
    D) Arrays of wildcards like new List<?>[10] are also illegal.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Arrays are reified (runtime-checked), while generics are erased. Mixing them breaks the JVM's type safety. (Note: Arrays of unbounded wildcards are technically allowed because ? is reifiable).

</details>

---
11. The Instanceof Restriction

Why is the following code illegal?
if (myList instanceof List<String>) { ... }

    A) Because the type <String> is erased at runtime, so the check cannot be performed.
    
    B) Because instanceof only works with primitive types.
    
    C) Because the variable myList must be a raw type first.
    
    D) Because generics don't support the instanceof keyword at all.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Since the JVM only sees a raw List, it cannot verify what the original intended generic type was.

</details>

---
12. Heap Pollution

What is "Heap Pollution" in the context of Java Generics?

    A) When a variable of a parameterized type refers to an object that is not of that type.
    
    B) When the JVM runs out of memory because of too many generic objects.
    
    C) When a generic method is called with a primitive argument.
    
    D) When the garbage collector cannot clean up generic bridge methods.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: This usually happens when mixing raw types and generic types, allowing the "wrong" type to be inserted into a collection silently.

</details>

---
13. Object vs. Wildcard

What is the primary difference between List<Object> and List<?>?

    A) List<Object> is a specific list for any object, while List<?> is a read-only view of an unknown type.
    
    B) List<?> can only hold null, but List<Object> can hold anything.
    
    C) There is no difference; they are erased to the same bytecode.
    
    D) List<Object> is safer than List<?>.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: List<Object> is invariant (it only accepts other List<Object>), while List<?> is the universal supertype of all lists.

</details>

---
14. Lower Bound Syntax

Which is the correct syntax for a Lower Bound wildcard?

    A) List<? super Integer>
    
    B) List<? below Integer>
    
    C) List<? extends super Integer>
    
    D) List<Integer super ?>

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: The super keyword establishes a lower bound, allowing Integer or any of its superclasses (like Number or Object).

</details>

---
15. Wildcard Capture

You have a method <T> void swap(List<T> list, int i, int j). You want to call it with a List<?>. Why does this require a "Helper" method?

    A) To "capture" the wildcard type into a named type parameter T.
    
    B) Because List<?> is not compatible with List<T>.
    
    C) To bypass the read-only restriction of wildcards.
    
    D) Helper methods are required for all static generic calls.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: The compiler cannot use ? directly for operations that require the same type in multiple places (like swapping elements). The helper "names" the unknown type so the compiler can track it.

</details>

---
16. Static Fields

Why can't you have a static field of type T in a generic class (e.g., static T item;)?

    A) Because static members are shared across all instances, but T varies per instance.
    
    B) Because static fields are erased before the constructor runs.
    
    C) Because T is not a valid type for fields.
    
    D) Because static fields must be final if they use generics.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: If you had Box<String> and Box<Integer>, they share the same static field—it would be impossible for one field to be both a String and an Integer simultaneously.

</details>

---
17. Methods in Non-Generic Classes

Can a non-generic class contain a generic method?

    A) Yes, as long as the method declares its own type parameters before the return type.
    
    B) No, only generic classes can have generic methods.
    
    C) Yes, but only if the method is static.
    
    D) No, because the compiler wouldn't know where to find the type parameter.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Generic methods are independent; a class doesn't need to be generic to host a method with its own "Permission Slip."

</details>

---
18. Interface Bounds

When defining bounds for an interface, which keyword do you use (e.g., <T ____ Serializable>)?

    A) extends
    
    B) implements
    
    C) with
    
    D) :

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Java Generics uses extends as the universal keyword for bounds, whether the supertype is a class or an interface.

</details>

---
19. Signature Collisions

Which of these code blocks will cause a "Signature Collision" compile error due to Type Erasure?

    A) void m(List<String> s) and void m(List<Integer> i)
    
    B) void m(String s) and void m(Integer i)
    
    C) void m(List<String> s) and void m(String s)
    
    D) void m(T t) and void m(Object o)

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Both erase to void m(List l), so they appear as duplicate methods to the JVM, violating overloading rules.

</details>

---
20. Syntax Placement

Where exactly does the "Permission Slip" <T> go in a generic static method signature?

    A) After the modifiers (like static) but before the return type.
    
    B) Immediately after the method name.
    
    C) At the very end of the signature.
    
    D) Inside the parameter parentheses.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: The compiler reads left-to-right; it needs to see the definition of T before it can process it as part of the return type.

</details>

---

21. Nested Generics

How do you correctly declare a Map where keys are String and values are a List of Integer?

    A) Map<String, List<Integer>> map;
    
    B) Map<String, List> map;
    
    C) Map<String, <Integer>List> map;
    
    D) Map<String, List<int>> map;

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Generics can be nested indefinitely. However, primitive types like int (Option D) are never allowed; you must use the wrapper Integer.

</details>

---

22. Multiple Type Parameters

What is the standard convention for a class that takes a Key and a Value?

    A) class Entry<K, V>
    
    B) class Entry<Key, Value>
    
    /C) class Entry<T1, T2>
    
    D) class Entry<T, U>

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: While any name is legal, Java conventions suggest K for Key, V for Value, T for Type, and E for Element.

</details>

---

23. Constructor Generics

Can a non-generic class have a generic constructor?

    A) Yes, the type parameter is placed before the class name in the constructor declaration.
    
    B) No, only generic classes can have generic constructors.
    
    C) Yes, the type parameter is placed before the return type (which is omitted for constructors).
    
    D) Yes, but the class must be declared abstract.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: C
    
    Rationale: A constructor can declare its own type parameters independent of the class. Syntax: public <T> MyClass(T t) { ... }.

</details>

---

24. Explicit Type Arguments

How do you explicitly provide a type argument to a static generic method Utils.process(val) if inference fails?

    A) Utils.<String>process(val)
    
    B) Utils.process<String>(val)
    
    C) Utils(String).process(val)
    
    D) Utils.process(val)<String>

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Explicit type arguments follow the dot but precede the method name.

</details>

---

25. Recursive Type Bounds

Which syntax describes a type T that can be compared to itself?

    A) <T extends Comparable<T>>
    
    B) <T implements Comparable<T>>
    
    C) <T extends Comparable<? super T>>
    
    D) Both A and C are common, but C is more flexible.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: D
    
    Rationale: T extends Comparable<T> is the basic recursive bound. T extends Comparable<? super T> (the PECS version) is the professional standard as it allows comparison to parents (e.g., Integer comparing via Number).

</details>

---

26. Varargs and Generics

What is the primary risk of using public <T> void doSomething(T... args)?

    A) Possible Heap Pollution.
    
    B) It's illegal to use varargs with generics.
    
    C) It prevents the use of the diamond operator.
    
    D) The JVM cannot calculate the array length.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Varargs are implemented as arrays. Since generic arrays are unsafe, mixing them can lead to Heap Pollution. Java requires @SafeVarargs to suppress warnings if the method is safe.

</details>

---

27. Intersection Types (Casting)

Which of these is a valid (though rare) intersection cast in Java?

    A) (Serializable & Runnable) obj
    
    B) (Serializable | Runnable) obj
    
    C) (Serializable, Runnable) obj
    
    D) Intersection types are only for definitions, not casts.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Java allows intersection types in casts using the & symbol, often used in lambda expressions to ensure a functional interface also implements a marker interface.

</details>

28. Generic Records (Java 14+)

What is the correct syntax for a generic Record?

    A) public record Box<T>(T content) {}
    
    B) public <T> record Box(T content) {}
    
    C) public record Box(T<content>) {}
    
    D) Records cannot be generic.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Records follow the same generic placement as classes: immediately after the name.

</details>

---

29. Type Inference with 'var'

In var list = new ArrayList<String>();, what is the type of list?

    A) ArrayList<String>
    
    B) List<String>
    
    C) ArrayList<Object>
    
    D) var (a new runtime type)

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: var infers the exact type on the right side. Since the right side is the concrete class ArrayList<String>, that is the inferred type.

</details>

---

30. Capture Conversion

When the compiler sees List<?> but needs a specific type to perform an operation, it internally creates a "captured" type. What is the notation for this in error messages?

    A) capture#1 of ?
    
    B) unknown type T
    
    C) ? extends Object
    
    D) Raw List

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Capture conversion is the process where the compiler "captures" the unknown type into a temporary internal name like capture#1.

</details>

---

31. Bounded Wildcard Return Types

Why is it generally considered Bad Practice to return List<? extends T> from a method?

    A) It forces the caller to use wildcards and restricts their ability to add to the list.
    
    B) The JVM cannot serialize wildcards.
    
    C) It causes memory leaks.
    
    D) Wildcards cannot be returned by value.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: "Be conservative in what you send, liberal in what you accept." Returning wildcards makes the API difficult to use. Return concrete types; accept wildcards.

</details>

---

32. Diamond Operator with Anonymous Classes

Until Java 9, why was Handler<String> h = new Handler<>() { ... }; illegal?

    A) Anonymous classes could not use the diamond operator for inference.
    
    B) Anonymous classes cannot be generic.
    
    C) The syntax was reserved for lambda expressions.
    
    D) It wasn't illegal; it was just rare.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Before Java 9, the diamond operator could not be used with anonymous inner classes. This restriction was lifted in Java 9.

</details>

---

33. Reifiable Types

Which of these is a Reifiable type (available at runtime)?

    A) List<?>
    
    B) List<String>
    
    C) T
    
    D) List<? extends Number>

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Most generics are non-reifiable. However, List<?> (unbounded) is reifiable because it doesn't contain any specific type information that could be erased.

</details>

---

34. Generic Method Shadowing

If a class Box<T> has a method public <T> void set(T t), what happens?

    A) The method's T shadows the class's T.
    
    B) The compiler throws a "Duplicate Type" error.
    
    C) The method's T is automatically constrained to the class's T.
    
    D) Shadowing is only possible with variables, not types.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Just like variable shadowing, a method-level type parameter hides a class-level parameter of the same name. This is often a source of bugs.
    
</details>

---

35. Inner Classes: Static vs Non-Static

Which statement is true about a non-static inner class Inner inside Outer<T>?

    A) Inner can automatically access T from Outer.
    
    B) Inner must declare its own <T>.
    
    C) Inner is erased to Object regardless of Outer.
    
    D) Non-static inner classes cannot be generic.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Non-static inner classes are tied to an instance of the outer class and can access its generic types. Static nested classes cannot.

</details>

---

36. Generic Exception Declaration

While you cannot catch a generic exception, can you throw one? (e.g., public <T extends Exception> void m() throws T)

    A) Yes, this is valid syntax and used in "sneaky throw" patterns.
    
    B) No, the throws clause must be a concrete class.
    
    C) Yes, but only if the method is private.
    
    D) No, because throws is a runtime check.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: You can declare that a method throws T. The compiler will treat it as throwing its bound (e.g., Exception) at runtime.

</details>

---
37. Arrays of Bounded Wildcards

Is List<? extends Number>[] array = new ArrayList[10]; a legal declaration?

    A) Yes, because a raw array creation followed by a cast to a wildcard-parameterized type is permitted (though it generates a warning).
    
    B) No, it's a generic array and thus strictly forbidden.
    
    C) Yes, but only if the size is a power of 2.
    
    D) Only in Java 17+.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: You can declare the variable. The creation new ArrayList[10] uses a raw type, which is allowed. The assignment works because wildcards are slightly more flexible than concrete types.

</details>

---

38. Target Typing

Java 8 improved type inference. In process(Collections.emptyList()), how does the compiler know the type of the empty list?

    A) Target Typing: It looks at the expected parameter type of the process method.
    
    B) It defaults to Object.
    
    C) It doesn't; it throws a "Cannot infer type" error.
    
    D) It uses the last type used in the file.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: Target typing allows the compiler to infer the type based on where the result is being passed.

</details>

---

39. Generic Lambda Expressions

Can a lambda expression itself be generic (e.g., (T t) -> { ... })?

    A) No, lambdas are instances of functional interfaces; the interface must be generic, not the lambda.
    
    B) Yes, using the <T> syntax before the parameters.
    
    C) Yes, but only in Java 21+.
    
    D) No, lambdas use Object exclusively.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: A lambda is just an implementation of a method. The "genericity" comes from the Functional Interface it is being assigned to.

</details>

---
40. Primitive Workarounds

Since List<int> is illegal, what is the most performant "workaround" in modern Java?

    A) Using primitive-specialized collections (like IntList in libraries or Valhalla features).
    
    B) Using List<Integer> and relying on autoboxing.
    
    C) Using a raw int[] array.
    
    D) Casting int to Object manually.

<details>
    <summary><b>Click to see the answer</b></summary>
    
    Correct Answer: A
    
    Rationale: While List<Integer> is the standard syntax, it suffers from memory overhead. Specialized libraries or the upcoming Project Valhalla provide true primitive generics.

</details>

---
$$\!TIP$$


Use these questions to prep for interviews! Understanding the "Why" behind these syntax rules is what separates senior developers from the rest.