package collections.list;

import java.util.Stack;

public class StackClass {
	/*
	 * 
	 * Stack methods
	 * 
	 * boolean empty() 
	 * E peek() 
	 * E pop() 
	 * E push(E e) 
	 * int search(Object o)  Returns the 1-based position where an object is on this stack
	 */

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();

		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);

		System.out.println(stack);//[10, 20, 30, 40, 50]

		System.out.println(stack.peek());//50
		System.out.println(stack.pop());//50
		
		System.out.println(stack.search(10));//4 
	}

}
