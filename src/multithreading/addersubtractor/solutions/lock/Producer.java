package multithreading.addersubtractor.solutions.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Integer maxSize;
	private Lock lock;

	public Producer(Queue<UnitOfWork> buffer, String name, Integer maxSize, Lock lock) {
		this.buffer = buffer;
		this.name = name;
		this.maxSize = maxSize;
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			if (buffer.size() < maxSize) {
				buffer.add(new UnitOfWork());
				System.out.println("Produced new item by " + name + " and the buffer size is " + buffer.size());
			}
			lock.unlock();
		}
	}
}
