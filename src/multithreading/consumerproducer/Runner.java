package multithreading.consumerproducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Runner {

	public static void main(String[] args) {
		SharedBuffer buffer = new SharedBuffer();
		Runnable producer = new Producer(buffer);
		Runnable consumer = new Consumer(buffer);

		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.execute(producer);
		executorService.execute(consumer);
		System.out.println(buffer.getBuffer());
		try {
			executorService.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(buffer.getBuffer());

	}

}
