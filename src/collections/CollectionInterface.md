A Collection represents a group of objects known as its elements. The Collection interface is used to pass around collections of objects where maximum generality is desired.

```java

		 * 1. boolean add(Element e) ;  if adds successfully returns true
		 * 2. boolean addAll(Collection c);  if adds successfully returns true
		 * 3. boolean remove(Object e) //Removes ONLY the first instance
		 * 4. boolean removeAll(Collection c);//Removes all the instance
		 * 5. boolean retainAll(Collection c);//Keeps only elements which which is from collection c
		 * 6. void clear()
		 * 7. int size()
		 * 8. boolean isEmpty()
		 * 9. boolean contains(Object e) ;
		 * 10.Object[] toArray -- returns Object array of the given collection
		 * 11.E[] toArray(new E()[0])
		 * eg : String[] arr = col.toArray(new String()[0]);
		 * Iterator<E> iterator()
		 * 
		 * 
		 * Other new Java 8 methods 
		 * 
		 * parallelStream()
		 * stream()
		 * removeIf(Predicate<? super E> filter)

```
		 


#####Traversing Collections
There are three ways to traverse collections: 

1. using aggregate operations 
2. with the for-each construct and 
3. by using Iterators.

###### Aggregate Operations

In JDK 8 and later, the preferred method of iterating over a collection is to obtain a stream and perform aggregate operations on it. Aggregate operations are often used in conjunction with lambda expressions to make programming more expressive, using less lines of code. The following code sequentially iterates through a collection of shapes and prints out the red objects:

```java
myShapesCollection.stream()
.filter(e -> e.getColor() == Color.RED)
.forEach(e -> System.out.println(e.getName()));
```

Likewise, you could easily request a parallel stream, which might make sense if the collection is large enough and your computer has enough cores:

```java
myShapesCollection.parallelStream()
.filter(e -> e.getColor() == Color.RED)
.forEach(e -> System.out.println(e.getName()));
```

There are many different ways to collect data with this API. For example, you might want to convert the elements of a Collection to String objects, then join them, separated by commas:

    String joined = elements.stream()
    .map(Object::toString)
    .collect(Collectors.joining(", "));

######for-each Construct

The for-each construct allows you to concisely traverse a collection or array using a for loop — see The for Statement. The following code uses the for-each construct to print out each element of a collection on a separate line.

for (Object o : collection)
    System.out.println(o);
    
######Iterators

An Iterator is an object that enables you to traverse through a collection and to remove elements from the collection selectively, if desired. You get an Iterator for a collection by calling its iterator method. The following is the Iterator interface.

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove(); //optional
}

```

The hasNext method returns true if the iteration has more elements, and the next method returns the next element in the iteration. The remove method removes the last element that was returned by next from the underlying Collection. The remove method may be called only once per call to next and throws an exception if this rule is violated.

Note that Iterator.remove is the only safe way to modify a collection during iteration;
the behavior is unspecified if the underlying collection is modified in any other way while the iteration is in progress.

######Use Iterator instead of the for-each construct when you need to:

Remove the current element. The for-each construct hides the iterator, so you cannot call remove. Therefore, the for-each construct is not usable for filtering.
Iterate over multiple collections in parallel.
The following method shows you how to use an Iterator to filter an arbitrary Collection — that is, traverse the collection removing specific elements.

```java
static void filter(Collection<?> c) {
    for (Iterator<?> it = c.iterator(); it.hasNext(); )
        if (!cond(it.next()))
            it.remove();
}
```
This simple piece of code is polymorphic, which means that it works for any Collection regardless of implementation. This example demonstrates how easy it is to write a polymorphic algorithm using the Java Collections Framework.

##### Collection Interface Array Operations

```java
Object[] a = c.toArray();
```

Suppose that c is known to contain only strings (perhaps because c is of type Collection<String>). The following snippet dumps the contents of c into a newly allocated array of String whose length is identical to the number of elements in c.

```java
String[] a = c.toArray(new String[0]);
```