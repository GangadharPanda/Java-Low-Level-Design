package collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListClass {

	public static void main(String[] args) {

		/**
		 * When to use ArrayList ?
		 * 
		 * 1. Storage and retrieval operations are more 
		 * 2. Random access is supported 
		 * 3.Based on the growable array (new size = (oldSize *3/2) + 1)
		 * 4. Default capacity of the ArrayList is 10 
		 * 5.When it reaches to the maximum capacity , an array of double size is created
		 * and all the elements are copied into the new array then the new element is
		 * added.
		 * 
		 */

		// Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess

		// RandomAccess is Marker Interface

		// Constructors -- 3
		List<Integer> list = new ArrayList<>();// Size: 0, Capacity: 0

		/*
		 * 
		 * For Default Constructor i.e new ArrayList<>() or new ArrayList<>(0)
		 * 
		 * just after creation -- Size: 0, Capacity: 0
		 * 
		 * When we add a the first item then only the capacity will be assigned to 10
		 * 
		 */
		list.add(10);// Size: 0, Capacity: 10
		list.add(20);
		list.add(30);

		List<Integer> list2 = new ArrayList<>(5); // Size: 0, Capacity: 5
		List<Integer> list3 = new ArrayList<>(list);

		list2.add(null);
		list2.add(null);

		System.out.println(list2);// [null, null]

		list2.remove(null);

		System.out.println(list2);// [null]

	}

}
