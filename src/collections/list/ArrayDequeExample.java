package collections.list;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class ArrayDequeExample {
	
	public static void main(String[] args) {
		Deque <Integer> stack = new ArrayDeque<>();
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
