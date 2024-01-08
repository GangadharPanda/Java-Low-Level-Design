package multithreading.consumerproducer.solutions.synchronizedsolution;

import java.util.Queue;

public class Consumer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;

	public Consumer(Queue<UnitOfWork> buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (buffer) {
				if (buffer.size() > 0) {
					buffer.remove();
					System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
				}
			}
		}
	}

}
