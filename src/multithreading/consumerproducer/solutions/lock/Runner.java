package multithreading.consumerproducer.solutions.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	public static void main(String[] args) {
		Queue<UnitOfWork> buffer = new ConcurrentLinkedDeque<>();
		Lock lock = new ReentrantLock();
		int maxSize = 10;
		for (int i = 0; i <= 3; i++) {
			Runnable producer = new Producer(buffer, "P" + i, maxSize, lock);
			Runnable consumer = new Consumer(buffer, "C" + i, lock);
			new Thread(producer).start();
			new Thread(consumer).start();
		}
	}

}
