package collections;

import java.util.Deque;
import java.util.LinkedList;

/*
 * The name deque is short for "double ended queue"
 * that supports element insertion and removal at both ends
 */

public class DequeInterface {
	
	/*Summary of Deque methods
	 * 
	 * 
	             Throws exception	     Returns special value    Throws exception	     Returns special value
	-------------|--------------------|--------------------------|--------------------|----------------------
	Insert	     |   addFirst(e)	  |  boolean offerFirst(e)   | addLast(e)	      |  boolean offerLast(e)
	Remove	     |   removeFirst()	  |  E       pollFirst()	 | removeLast()	      |  E       pollLast()
	Examine	     |   getFirst()	  	  |  E       peekFirst()     | getLast()	  	  |  E       peekLast()
	*/
	
	
	//Note : if we try to call remove() or element() method on empty Q we will get "NoSuchElementException"
	//But , calling poll() or peek() on empty will not raise any exception
	
	public static void main(String[] args) {
		Deque <Integer> stack = new LinkedList<>();
		//Implement Stack using Deque
		
		/*
		 * idea is simple 
		 * 
		 * push should be O(1) operation
		 * pop should be O(1) operation
		 * peek should be O(1) operation
		 * 
		 */
		
		stack.offerFirst(1);
		stack.offerFirst(2);
		stack.offerFirst(3);
		stack.offerFirst(4);
		stack.offerFirst(5);
		System.out.println(stack);//[5, 4, 3, 2, 1]
		
		stack.pollFirst();
		System.out.println(stack.peekFirst());
		
		
		
		//Implement Queue using Deque
		
		/*
		 * idea is simple , insert from front , and remove from last
		 * 
		 * o should be O(1) operation
		 * pop should be O(1) operation
		 * peek should be O(1) operation
		 * 
		 */
		
		
		Deque <Integer> q = new LinkedList<>();
		
		q.offerLast(10);
		q.offerLast(20);
		q.offerLast(30);
		q.offerLast(40);
		q.offerLast(50);
		
		System.out.println(q);//[10, 20, 30, 40, 50]
		
		// Remove using pollFirst()
		
		System.out.println(q.pollFirst());//10
		
		System.out.println(q);//[20, 30, 40, 50]
		
		System.out.println(q.peekFirst());//20
	}
		

}
