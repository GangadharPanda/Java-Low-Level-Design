package multithreading.addersubtractor.solutions.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Integer maxSize;

	private Semaphore forProducer;
	private Semaphore forConsumer;

	public Producer(Queue<UnitOfWork> buffer, String name, Integer maxSize, Semaphore forProducer,
			Semaphore forConsumer) {
		super();
		this.buffer = buffer;
		this.name = name;
		this.maxSize = maxSize;
		this.forProducer = forProducer;
		this.forConsumer = forConsumer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				forProducer.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buffer.add(new UnitOfWork());
			System.out.println("Produced new item by " + name + " and the buffer size is " + buffer.size());
			forConsumer.release();
		}
	}
}
