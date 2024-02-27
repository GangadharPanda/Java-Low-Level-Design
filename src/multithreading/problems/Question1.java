package multithreading.problems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 
 * Write a Java program to create and start multiple threads that increment a shared counter variable concurrently.
 */

public class Question1 {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(2);
		SharedResource resource = new SharedResource(0);

		for (int i = 1; i <= 10; i++) {
			service.execute(new NumberIncrementor(resource));
		}
		service.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println("PRINTED BY THE MAIN THREAD -- " + resource.getValue());
	}
}
