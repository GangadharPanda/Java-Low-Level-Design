package multithreading.addersubtractor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnerWithExecutors {

	public static void main(String[] args) throws InterruptedException {
		SharedResorce c = new SharedResorce(0);// Common object
		ExecutorService executorService = Executors.newCachedThreadPool();
		Runnable adder = new Adder(c);
		Runnable subtractor = new Subtractor(c);
		executorService.execute(adder);
		executorService.execute(subtractor);

		try {
			executorService.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.getCount();

	}

}
