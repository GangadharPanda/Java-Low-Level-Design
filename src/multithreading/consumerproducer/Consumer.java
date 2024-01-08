package multithreading.consumerproducer;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

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
			if (buffer.size() > 0) {
				buffer.remove();
				System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
				try {
	                TimeUnit.SECONDS.sleep(2);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
		}
	}

}
