package multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentCollectionExample implements Runnable {

	private static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) {

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		Thread t = new Thread(new ConcurrentCollectionExample());
		t.start();

		list.add(6);
		list.add(7);

		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);

	}

	public void run() {
		Iterator<Integer> itr = list.listIterator();

		while (itr.hasNext()) {
			Integer i = itr.next();
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
