package multithreading.consumerproducer.solutions.semaphore;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

public class Runner {

	public static void main(String[] args) {
		Queue<UnitOfWork> buffer = new ConcurrentLinkedDeque<>();
		int maxSize = 10;

		Semaphore forConsumer = new Semaphore(0);
		Semaphore forProducer = new Semaphore(maxSize);

		for (int i = 0; i <= 3; i++) {
			Runnable producer = new Producer(buffer, "P" + i, maxSize, forProducer, forConsumer);
			Runnable consumer = new Consumer(buffer, "C" + i, forProducer, forConsumer);
			new Thread(producer).start();
			new Thread(consumer).start();
		}
	}

}
