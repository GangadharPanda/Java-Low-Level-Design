package multithreading.addersubtractor.solutions.synchronizedsolution;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Runner {

	public static void main(String[] args) {
		Queue<UnitOfWork> buffer = new ConcurrentLinkedDeque<>();
		int maxSize = 10;
		for (int i = 0; i <= 3; i++) {
			Runnable producer = new Producer(buffer, "Producer " + i, maxSize);
			Runnable consumer = new Consumer(buffer, "Consumer " + i);
			new Thread(producer).start();
			new Thread(consumer).start();
		}
	}

}
