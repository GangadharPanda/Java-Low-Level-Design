package collections.set;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		
		/*
		 * TreeSet()
			Constructs a new, empty tree set, sorted according to the natural ordering of its elements.
			The object must be implementing Comparable<E> Interface
			
		  TreeSet(SortedSet<E> s)
			Constructs a new tree set containing the same elements and using the same ordering as the specified sorted set.
			
	      TreeSet(Comparator<? super E> comparator)
			Constructs a new, empty tree set, sorted according to the specified comparator.
			
		  TreeSet(Collection<? extends E> c)
			Constructs a new tree set containing the elements in the specified collection, sorted according to the natural ordering of its elements.
		 * 
		 */

		SortedSet<String> tree = new TreeSet<>();

		tree.add(new String("Hi"));
		System.out.println(tree);

		// ClassCastException : Student cannot be cast to class java.lang.Comparable
		SortedSet<Student> tree2 = new TreeSet<>();
		tree2.add(new Student());

	}

}
