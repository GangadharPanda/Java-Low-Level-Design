package multithreading.consumerproducer.solutions.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;

	private Lock lock;

	public Consumer(Queue<UnitOfWork> buffer, String name, Lock lock) {
		this.buffer = buffer;
		this.name = name;
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			if (buffer.size() > 0) {
				buffer.remove();
				System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
			}
			lock.unlock();
		}
	}

}
