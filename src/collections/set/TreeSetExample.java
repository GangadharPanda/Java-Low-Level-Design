package collections.set;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		
		// Note : TreeSet uses a self-balancing binary search tree (BST) is any node-based binary search tree
		// that automatically keeps its height (maximal number of levels below the root) small in the face of 
		// arbitrary item insertions and deletions.

		/*
		 * TreeSet() 
		 * Constructs a new, empty tree set, sorted according to the natural
		 * ordering of its elements. The object must be implementing Comparable<E>
		 * Interface
		 * 
		 * TreeSet(SortedSet<E> s) 
		 * Constructs a new tree set containing the same
		 * elements and using the same ordering as the specified sorted set.
		 * 
		 * TreeSet(Comparator<? super E> comparator) 
		 * Constructs a new, empty tree set,
		 * sorted according to the specified comparator.
		 * 
		 * TreeSet(Collection<? extends E> c) 
		 * Constructs a new tree set containing the
		 * elements in the specified collection, sorted according to the natural
		 * ordering of its elements.
		 * 
		 */

		SortedSet<Student> tree = new TreeSet<>();
		tree.add(new Student("A", 1));
		
		System.out.println("-------One Comparison with A------------");
		tree.add(new Student("B", 2));
		System.out.println("-------Two Comparison with A & B-------");
		tree.add(new Student("C", 3));
		
		
		/**
		 *  
		 *  A 
		 *   \ 
		 *    B
		 *     \ 
		 *      C
		 *      
		 *  
		 * Now the self balancing will happen 
		 * 
		 *    B
		 *   / \
		 *  A   C
		 * 
		 * 
		 */
		System.out.println("-------Two Comparison with B & C-------");
		tree.add(new Student("D", 4));
		
		System.out.println(tree);
		/*
		 * 
		 *  this Student [name=A, id =]
			Parameter Student [name=A, id = 1]
			
			While inserting B
			-------One Comparison with A------------
			
			this Student [name=B, id = 2]
			Parameter Student [name=A, id = 1]
			
			While inserting C
			-------Two Comparison with A & B-------
			
			this Student [name=C, id = 3]
			Parameter Student [name=A, id = 1]
			this Student [name=C, id = 3]
			Parameter Student [name=B, id = 2]
			
			While inserting D
			-------Two Comparison with B & C-------
			
			this Student [name=D, id = 4]
			Parameter Student [name=B, id = 2]
			this Student [name=D, id = 4]
			Parameter Student [name=C, id = 3]
		 * 
		 * 
		 */
		
		
		SortedSet<Integer> intTree = new TreeSet<>();
		
		
		

	}

}
