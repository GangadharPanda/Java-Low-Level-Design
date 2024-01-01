package multithreading.consumerproducer;

import java.util.Queue;

public class Producer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Integer maxSize;

	public Producer(Queue<UnitOfWork> buffer, String name, Integer maxSize) {
		this.buffer = buffer;
		this.name = name;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			if (buffer.size() < maxSize) {
				buffer.add(new UnitOfWork());
				System.out.println("Produced new item by " + name + " and the buffer size is " + buffer.size());
			}
		}
	}
}
