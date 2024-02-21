package collections.set;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {

		SortedSet<StringBuilder> tree = new TreeSet<>();
		// The class storing in tree must be comparable , if we are using the default
		// TreeSet constructor

		tree.add(new StringBuilder("Hi"));
		System.out.println(tree);//[Hi]

		// ClassCastException : Student cannot be cast to class java.lang.Comparable
		SortedSet<Student> tree2 = new TreeSet<>();
		tree2.add(new Student());

	}

}
