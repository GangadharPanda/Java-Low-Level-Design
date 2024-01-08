package multithreading.addersubtractor.solutions.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Semaphore forProducer;
	private Semaphore forConsumer;

	public Consumer(Queue<UnitOfWork> buffer, String name, Semaphore forProducer, Semaphore forConsumer) {
		super();
		this.buffer = buffer;
		this.name = name;
		this.forProducer = forProducer;
		this.forConsumer = forConsumer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				forConsumer.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buffer.remove();
			System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
			forProducer.release();
		}
	}

}
