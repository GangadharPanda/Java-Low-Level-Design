package collections.list;

import java.util.LinkedList;
import java.util.Queue;

/*
 * LinkedList class can be used as a Queue datastructure
 * because it implements the Queue interface
 */

public class QueueInterface {
	
	/*Summary of Queue methods
	             Throws exception	  Returns special value
	-------------|----------------|--------------------------
	Insert	     |   add(e)	      |        boolean offer(e)
	Remove	     |   remove()	  |        E       poll()
	Examine	     |   element()	  |        E       peek()
	*/
	
	
	//Note : if we try to call remove() or element() method on empty Q we will get "NoSuchElementException"
	//But , calling poll() or peek() on empty will not raise any exception
	
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		
		
		q.peek();
		q.poll();
		
		q.offer(1);
		q.offer(2);
		q.offer(3);
		q.offer(4);
		q.offer(5);
		q.offer(6);
		
		System.out.println(q);//[1, 2, 3, 4, 5, 6]
		
		System.out.println(q.poll());// 1
		System.out.println(q);//[2, 3, 4, 5, 6]
		System.out.println(q.peek());//2 
		System.out.println(q);//[2, 3, 4, 5, 6]
	}
		

}
