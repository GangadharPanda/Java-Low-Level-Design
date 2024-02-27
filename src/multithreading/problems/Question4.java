package multithreading.problems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Write a Java program that uses the ReentrantLock class to synchronize access
 * to a shared resource among multiple threads.
 * 
 * @author USER
 *
 */

public class Question4 {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Semaphore semaphore = new Semaphore(1);
		SharedResource resource = new SharedResourceWithSemaphore(0, semaphore);

		for (int i = 1; i <= 3; i++) {
			executor.execute(new NumberIncrementor(resource));
		}
		executor.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println("PRINTED BY THE MAIN THREAD -- " + resource.getValue());
	}

}
