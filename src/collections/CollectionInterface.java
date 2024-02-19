package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionInterface {

	public static void main(String[] args) {

		Collection<String> col = new ArrayList<>();

		// Methods in java collection interface

		/*
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
		 */

		// Add a single value
		col.add("Hi");
		col.add("Hello");
		col.add("How");
		col.add("are");
		col.add("you?");
		System.out.println(col);// [Hi, Hello, How, are, you?]

		// -------------------------

		Collection<String> col2 = new ArrayList<>();
		col2.add("Hi");
		col2.add("I");
		col2.add("am");
		col2.add("Good");
		col2.add("Thank You");
		System.out.println(col2);// [Hi, I, am, Good, Thank You]

		// add another collection
		col.addAll(col2);

		//System.out.println(col);// [Hi, Hello, How, are, you?, Hi, I, am, Good, Thank You]

		// Remove one element
		col.remove("Hi");

		System.out.println(col);// [Hello, How, are, you?, Hi, I, am, Good, Thank You]
		// NOTE : This does not delete the 2nd occurrence of 'Hi'
		
		System.out.println(col.contains("Hi"));//true
		
		col.addAll(col2);
		
		System.out.println(col);//[Hello, How, are, you?, Hi, I, am, Good, Thank You, Hi, I, am, Good, Thank You]
		
		// Removes all the occurrences of collections (here we added col2 twice and it's removing both) 
		col.removeAll(col2);
		
		System.out.println(col);//[Hello, How, are, you?]
		
		Object [] arr = col.toArray();
		
		
		for(Object a : arr) {
			System.out.println(a);
		}
		
		String[] arrString = col.toArray(new String[0]);
		
		for(String a : arrString) {
			System.out.println(a);
		}
		
		Iterator<String> iterator = col.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
				

		// Remove everything
		col.clear();

		System.out.println(col);// []
		
		/**
		 * Questions based
		 */

	}

}
