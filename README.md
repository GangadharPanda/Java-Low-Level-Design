# Java Synchronization

When multiple threads access shared resources, it can lead to synchronization issues. Let’s explore the need for synchronization, the issues that arise without it, and the solutions available in Java.

# What is the issue if we don't use synchronization?

Let's look at the below example , where a share resource is used by multiple threads.
1. ShareResource : This is the object shared among two threads.
2. Adder : Adder thread increment the share variable 100 times.
3. Subtractor : Subtractor thread decrement the shared variable 100 times.
4. Expected Result : The value of the shared variable should be 0.
5. Actual result : The value is non zero.
```
package multithreading;

public class Adder extends Thread {

	SharedResorce sharedResorce;

	public Adder(SharedResorce sharedResorce) {
		this.sharedResorce = sharedResorce;
	}

	public void run() {
		System.out.println("--------- Adder started ----------");
		for (int i = 0; i < 100; i++) {
			sharedResorce.increment();
		}
		System.out.println("--------- Adder Completed ----------");
	}

}
```
```
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
```
```
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
```
```

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
		 * Intentionally , the threads are sleeping for 10 ms , to ensure both
		 * of these threads gets the chance to update the shared variable.
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
```


# How to fix this?
There are 3 ways to fix this issue 
1. synchoronized keyword
2. Mutex lock
3. Binary Semaphores

## synchronized keyword

If we synchoronize both the methods increment() and decrement(), only one thread can execute either method at a time . 
i.e if Adder thread is trying to call increment method , no other thread can execute increment or decrement mentod at the same time.

find the below code , 

```
package multithreading;

public class SharedResorceWithSynchronized extends SharedResorce{

	public SharedResorceWithSynchronized(int count) {
		super(count);
	}

	public synchronized void increment() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.increment();
	}

	public synchronized void decrement() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.decrement();
	}
}
```
```
package multithreading;

public class Runner {

	public static void main(String[] args) {
		SharedResorce sharedResource = new SharedResorceWithSynchronized(0);
		Thread adder = new Adder(sharedResource);
		Thread subtractor = new Subtractor(sharedResource);

		adder.start();
		subtractor.start();
		try {
			adder.join();
			subtractor.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(sharedResource.getCount());// 0 , as EXECTED
	}
}
```


## Mutex Lock

```
package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResorceWithReentrantLock extends SharedResorce {
	Lock lock = new ReentrantLock();

	public SharedResorceWithReentrantLock(int count) {
		super(count);
	}

	public void increment() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.lock();//aquire the lock
		super.increment();
		lock.unlock();//release the lock
	}

	public void decrement() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.lock();//aquire the lock
		super.decrement();
		lock.unlock();//release the lock
	}
}

```


## Binary Semaphore

```
package multithreading;

import java.util.concurrent.Semaphore;

public class SharedResorceSemaphores extends SharedResorce {
	Semaphore semaphore = new Semaphore(1);

	public SharedResorceSemaphores(int count) {
		super(count);
	}

	public void increment() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.increment();
		semaphore.release();
	}

	public void decrement() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.decrement();
		semaphore.release();
	}
}
```
