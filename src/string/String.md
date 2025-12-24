


### String is immutable, how can the intern() method 'add' it to the pool?
Answer: It doesn't change the String object itself. It updates the internal JVM StringTable (a map) to include a reference to that String object.

### Why is it a security risk to store passwords in a String instead of a char[]?
Answer: Because Strings are immutable and stored in the SCP, they stay in memory until the next GC cycle (which could be a long time). You cannot "wipe" the data. With a char[], you can manually set all elements to zero immediately after use.

### How does String.hashCode() work, and why is it cached?
Answer: It uses a formula: $s[0]*31^{(n-1)} + s[1]*31^{(n-2)} + ...$.
Since Strings are immutable, the hash is calculated once and stored in a private field (hash). This makes Strings perfect keys for HashMap.

### Memory Check: How many objects are created by: 
```String s = new String(new char[]{'a', 'b', 'c'});? ```
Is any object created in the SCP?

### Advanced: 
If you are processing 1 million unique IDs from a database, should you call .intern() on all of them? Why or why not?

Why? The SCP is backed by a fixed-size (but tunable) Hashtable. If you intern 1 million unique strings, you will cause massive Hash Collisions in that table. This significantly slows down the intern() method and increases the time it takes for the JVM to look up any string literal, hurting overall application performance.

### Deep Dive: String Interning "Small Things"
Let's look at how intern() behaves differently across Java versions, as this often comes up in interviews.

The Evolution of Interning
#### Before Java 7,
The SCP was in PermGen. Interning a string meant copying the object into that restricted memory space.

#### From Java 7 onwards: 
If you call s.intern() and that string is not in the SCP:

The JVM does not create a new copy in the SCP.

Instead, it stores the reference of the object from the General Heap into the SCP table.

This saves memory because you don't have two identical objects; both the heap and the SCP refer to the same instance.

Try these out 
```java
String s1 = new String("interview"); // Line 1
String s2 = s1.intern();             // Line 2
System.out.println(s1 == s2);        // Output?
```
##### Line 1: Because you typed the literal "interview" inside the parentheses, the JVM immediately puts "interview" into the SCP. Then, new String() creates a copy in the heap.

##### s1 -> Points to Heap.

##### SCP -> Already contains "interview".

##### Line 2: s1.intern() checks the SCP. It sees "interview" is already there (from Line 1). It returns the reference to that existing pool object.

##### s2 -> Points to SCP.

##### Result: s1 (Heap) == s2 (SCP) is false.

#### When would it be true? (The Java 7+ Change) 

##### It only becomes true if the string is created dynamically (without a literal) so that the SCP is empty when intern() is called.

```java
// Dynamically created (no "interview" literal used yet)
String s1 = new StringBuilder("inter").append("view").toString();

String s2 = s1.intern();

System.out.println(s1 == s2); // Output: true (Only in Java 7+)
```
##### The Intuition:

##### Java 6 (Old Way): intern() would see the SCP is empty, create a copy of "interview" in the SCP, and return that copy's address. s1 and s2 would be different.

##### Java 7+ (New Way): intern() sees the SCP is empty. To save memory, it doesn't make a copy. It simply stores the address of the heap object (s1) into the SCP. Now, s2 points to exactly the same place as s1.

---

## 📝 Part 2: 30 Tricky Output-Based Questions

### 1. The Classic Comparison
```java
String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");
System.out.println(s1 == s2); 
System.out.println(s1 == s3);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true, false

Explanation: s1 and s2 point to the same object in the SCP. s3 is a new object in the general heap. </details>

2. The intern() Identity
```java

String s1 = new String("Java");
String s2 = s1.intern();
String s3 = "Java";
System.out.println(s1 == s2);
System.out.println(s2 == s3);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: false, true

Explanation: s1 is in the heap. s2 is the reference from the SCP (returned by intern()). s3 is a literal pointing to that same SCP reference. </details>

3. Object Creation Count
How many objects are created by:

```String s = new String("Spring"); (Assume pool is empty).```

<details> <summary><b>Answer & Explanation</b></summary> Answer: 2

Explanation: 1 in the SCP (literal) and 1 in the heap (the new keyword constructor). </details>

4. Constant Folding
```java

String s1 = "Java17";
String s2 = "Java" + 17;
System.out.println(s1 == s2);

```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: The compiler optimizes literal addition at compile time. Both become "Java17" in the SCP. </details>

5. Variable Concatenation
```java

String base = "Java";
String s1 = "Java17";
String s2 = base + 17;
System.out.println(s1 == s2);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: false

Explanation: 'base' is a variable; concatenation happens at runtime, creating a new heap object via StringBuilder or invokedynamic. </details>

6. The final Constant Twist
```java

final String base = "Java";
String s1 = "Java17";
String s2 = base + 17;
System.out.println(s1 == s2);
```

<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: 'final' makes 'base' a constant expression. The compiler treats it as a literal and performs folding. </details>

7. Explicit Method vs Pool
```java

String s1 = "A".concat("B");
String s2 = "AB";
System.out.println(s1 == s2);
```

<details> <summary><b>Answer & Explanation</b></summary> Output: false

Explanation: .concat() always returns a new String object in the heap. </details>

8. Reserved "java" Word
```java

String s1 = new StringBuilder("ja").append("va").toString();
System.out.println(s1.intern() == s1);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: false

Explanation: "java" is a reserved literal pre-loaded into the SCP by the JVM. intern() returns that pre-existing reference. </details>

9. Reference Interning (Java 7+)
```java

String s1 = new StringBuilder("Senior").append("Dev").toString();
System.out.println(s1.intern() == s1);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: Since "SeniorDev" isn't in the pool yet, intern() stores the reference of the heap object s1 into the pool instead of copying it. </details>

10. Chained Literals
```java

String s1 = "a" + "b" + "c";
String s2 = "abc";
System.out.println(s1 == s2);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: All parts are literals; the compiler folds them into one "abc" constant. </details>

11. Compact Strings (Java 17)
How many bytes does "ABC" take (excluding object header)?

<details> <summary><b>Answer & Explanation</b></summary> Answer: 3 bytes.

Explanation: Latin-1 characters use 1 byte per char in Java 9+ (Compact Strings). </details>

12. Emoji Storage Footprint
How many bytes does "🚀" take in memory?

<details> <summary><b>Answer & Explanation</b></summary> Answer: 4 bytes.

Explanation: Non-Latin-1 characters revert to UTF-16 (2 bytes per code unit). </details>

13. Deduplication vs SCP
Does -XX:+UseStringDeduplication affect the String Constant Pool?

<details> <summary><b>Answer & Explanation</b></summary> Answer: No.

Explanation: It works only on heap-based strings during GC by merging internal byte arrays. </details>

14. Substring Leak Fix
Does s.substring(0, 1) create a memory leak in Java 17?

<details> <summary><b>Answer & Explanation</b></summary> Answer: No.

Explanation: Modern Java copies the underlying array rather than sharing the parent’s array. </details>

15. The hash field Initialization
Is the 'hash' field in the String class final?

<details> <summary><b>Answer & Explanation</b></summary> Answer: No.

Explanation: It is 0 until hashCode() is first called (Lazy initialization). </details>

16. Loop StringBuilder Overhead
```java

String s = "";
for(int i=0; i<5; i++) { s += i; }
```
How many StringBuilder objects are created?

<details> <summary><b>Answer & Explanation</b></summary> Answer: 5.

Explanation: Each += in a loop triggers a new StringBuilder and toString() conversion. </details>

17. Object Reference Typing
```java

String s1 = "hello";
Object s2 = new String("hello").intern();
System.out.println(s1 == s2);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: intern() returns the SCP reference regardless of the variable type. </details>

18. UpperCase Pool Interaction
```java

String s1 = "HI";
String s2 = "hi".toUpperCase();
System.out.println(s1 == s2.intern());
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: toUpperCase() creates a new heap string; intern() maps it to the existing pool entry. </details>

19. Empty String Pooling
```java

String s1 = "";
String s2 = new String("");
System.out.println(s1 == s2.intern());
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: The empty string is a valid entry in the pool. </details>

20. Null Concatenation Result
```java

String s1 = null;
String s2 = "abc" + s1;
System.out.println(s2);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: abcnull

Explanation: The concatenation process treats the null reference as the literal string "null". </details>

21. Character Array Identity
```java

char[] c = {'a'};
String s1 = new String(c);
String s2 = "a";
System.out.println(s1 == s2);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: false

Explanation: Array constructors always create a new heap object. </details>

22. Pool Tuning Flag
What is the JVM flag for tuning the String Table buckets?

<details> <summary><b>Answer & Explanation</b></summary> Answer: -XX:StringTableSize </details>

23. Equals vs Reference
```java

String s1 = new String("x");
String s2 = new String("x");
System.out.println(s1.equals(s2));
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: .equals() compares content, not memory addresses. </details>

<details> <summary><b>Answer & Explanation</b></summary> Answer: StringBuilder (no synchronization overhead). </details>

24. The toString() Identity
```java

String s = "a";
System.out.println(s == s.toString());
```
<details> <summary><b>Answer & Explanation</b></summary> Output: true

Explanation: String.toString() returns the same reference (this). </details>

25. SCP Physical Location (Modern JVM)
Where does the SCP reside in Java 17?

<details> <summary><b>Answer & Explanation</b></summary> Answer: The Heap. </details>

26. Security Best Practice
Why is char[] used for passwords?

<details> <summary><b>Answer & Explanation</b></summary> Answer: It can be cleared/wiped manually via Arrays.fill(). Strings cannot be wiped. </details>

27. Overloading specificity (null)
```java

public void call(Object o) { System.out.println(1); }
public void call(String s) { System.out.println(2); }

// Which is called?
call(null);
```
<details> <summary><b>Answer & Explanation</b></summary> Output: 2

Explanation: Java resolves to the most specific matching type in the hierarchy. </details>