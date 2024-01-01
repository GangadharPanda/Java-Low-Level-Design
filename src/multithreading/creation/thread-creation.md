# How to Create a Thread in Java

To implement multithreading, Java defines two ways by which a thread can be created.

1. By extending the Thread class.
2. By implementing the Runnable interface.

## Extending Thread class

Create a thread by a new class that extends Thread class and create an instance of that class. 
The extending class must override run() method which is the entry point of new thread.

Below example creates a thread which calculates the Square of given number

```
package multithreading.creation;

public class SquarePrinterUsingThreads extends Thread {

	private Integer number;
	private String name;

	SquarePrinterUsingThreads(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(
				"Square of the number " + number + " = " + (number * number) + " calculated By Thread " + name);
	}

}
```

## Implementing the Runnable Interface

The easiest way to create a thread is to create a class that implements the runnable interface. After implementing runnable interface, the class needs to implement the run() method.

Below example creates a thread which calculates the Square of given number

```
package multithreading.creation;

public class SquarePrinterUsingRunnable implements Runnable {
	private Integer number;
	private String name;

	SquarePrinterUsingRunnable(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(
				"Square of the number " + number + " = " + (number * number) + " calculated By Runnable " + name);

	}
}

package multithreading.creation;

import java.util.List;

public class Runner {

	public static void main(String[] args) {
		List<Integer> nums = List.of(1, 2, 3, 4, 5);

		for (Integer num : nums) {
			Thread squarePrinter = new SquarePrinterUsingThreads(num, "Square Printer");
			squarePrinter.start();
		}
		
		//Output 
		
		/**
		 * 	Square of the number 1 = 1 calculated By Square Printer thread
		   	Square of the number 2 = 4 calculated By Square Printer thread
			Square of the number 3 = 9 calculated By Square Printer thread
			Square of the number 4 = 16 calculated By Square Printer thread
			Square of the number 5 = 25 calculated By Square Printer thread
		 * 
		 */
		// Using Runnable 
		for (Integer num : nums) {
			Runnable squarePrinterRunnable = new SquarePrinterUsingRunnable(num, "Square Printer");
			Thread thread = new Thread(squarePrinterRunnable);
			thread.start();
		}
		
		
	}

}
```

# Problem with both of the above approach is , the thread can not return a value 

However, one feature lacking in  Runnable is that we cannot make a thread return result when it terminates,
i.e. when run() completes. 
For supporting this feature, the Callable interface is present in Java.

```
package multithreading.creation;

import java.util.concurrent.Callable;

public class SquareReturnerUsingCallable implements Callable<Integer> {

	private Integer number;

	SquareReturnerUsingCallable(Integer number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("Calculating the Square of " + number);
		return number * number;
	}

}
```


```
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
				// result.get() is a BLOCKing method call
				// So it will sleep and wait till the result is calculated

			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
```

