# Java Synchronization

When multiple threads access shared resources, it can lead to synchronization issues. Let’s explore the need for synchronization, the issues that arise without it, and the solutions available in Java.

# What is the issue if we don't use synchronization?

Let's look at the below example , where a share resource is used by multiple threads.
ShareResource : This is the object shared among two threads.
Adder : Adder thread increment the share variable 100 times.
Subtractor : Subtractor thread decrement the shared variable 100 times.
Expected Result : The value of the shared variable should be 0.
Actual result : The value is non zero.

package multithreading;
public class Adder extends Thread{
	SharedResorce sharedResorce;
	public Adder(SharedResorce sharedResorce) {
		this.sharedResorce = sharedResorce;
	}
	public void run() {
		System.out.println("--------- Adder started ----------");
		for(int i = 0; i < 100; i++) {
			sharedResorce.increment();
		}
		System.out.println("--------- Adder Completed ----------");
	}
}

package multithreading;
public class Subtractor extends Thread {
	SharedResorce sharedResorce;
	public Subtractor(SharedResorce sharedResorce) {
		this.sharedResorce = sharedResorce;
	}
	public void run() {
		System.out.println("--------- Subtractor started ----------");
		for (int i = 0; i < 100; i++) {
			sharedResorce.decrement();
		}
		System.out.println("--------- Subtractor Completed ----------");
	}
}

package multithreading;

public class SharedResorce {
	private int count = 0;

	public SharedResorce(int count) {
		this.count = count;
	}

	public void  increment() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}

	public void decrement() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count--;
	}

	public int getCount() {
		return count;
	}
}

package multithreading;

public class Runner {

	public static void main(String[] args) {
		SharedResorce sharedResource = new SharedResorce(0);
		Thread adder = new Adder(sharedResource);
		Thread subtractor = new Subtractor(sharedResource);

		/*
		 * When we try to use multiple threads on a share object , if the shared
		 * resource is not synchronized it can cause inconsistent results.
		 * 
		 * Adder thread increments the shared variable 100 times , and Subtractor thread
		 * decrements the value 100 times.
		 * 
		 * Intentionally , the threads are sleeping for 10 ms , to ensure another both
		 * of these threads gets the chance to execute the shared variable
		 * 
		 */

		adder.start();
		subtractor.start();
		try {
			adder.join();
			subtractor.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// The correct value of the output should be 0 , but it's non 0
		System.out.println(sharedResource.getCount());
  }
}


## Installation

Provide instructions on how to install and set up the project.

## Usage

Explain how to use your project.

## Code Explanation

Include a section where you explain the main components of your code.
