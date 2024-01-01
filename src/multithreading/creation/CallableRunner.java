package multithreading.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRunner {

	public static void main(String[] args) {
		// Create a list of Integers
		List<Integer> nums = List.of(1, 2, 3, 4, 5);

		// Create the ExecutorsService , because we don't have any Thread Constructor
		// which have Callable as an parameter
		ExecutorService exs = Executors.newCachedThreadPool();

		// Store the result calculated by the thread

		List<Future<Integer>> results = new ArrayList<>();

		// Submit all these values to the executor
		for (Integer num : nums) {
			Callable<Integer> thread = new SquareReturnerUsingCallable(num);
			results.add(exs.submit(thread));
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Future<Integer> result : results) {
			try {
				System.out.println(result.get());
				// This is a BLOCKing method call
				// So it will sleep and wait till the result is calculated

			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
