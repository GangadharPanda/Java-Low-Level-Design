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

🏆 Answer Key

Question

Correct Answer

Topic

1

A

Class Definition Syntax

2

A

Static Method Permission Slip

3

A

Upper Bounds

4

A

Multiple Bounds (&)

5

A

Diamond Operator

6

A

PECS Producer Restriction

7

A

PECS Consumer Reading

8

A

Consequence of Erasure

9

A

Bridge Methods

10

A

Generic Array Restrictions

11

A

Instanceof Limitations

12

A

Heap Pollution Definition

13

A

List<?> vs List<Object>

14

A

Lower Bound Syntax

15

A

Wildcard Capture Helper

16

A

Static Context Rules

17

A

Generic Methods in Raw Classes

18

A

Interface Bounds Keyword

19

A

Overloading Signature Collision

20

A

Generic Parameter Placement

$$\!TIP$$


Use these questions to prep for interviews! Understanding the "Why" behind these syntax rules is what separates senior developers from the rest.