# Java Synchronization

When multiple threads access shared resources, it can lead to synchronization issues. Let’s explore the need for synchronization, the issues that arise without it, and the solutions available in Java.

```java 

package multithreading.addersubtractor.solutions.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;

	private Lock lock;

	public Consumer(Queue<UnitOfWork> buffer, String name, Lock lock) {
		this.buffer = buffer;
		this.name = name;
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			if (buffer.size() > 0) {
				buffer.remove();
				System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
			}
			lock.unlock();
		}
	}

}
```

```java

package multithreading.addersubtractor.solutions.lock;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Integer maxSize;
	private Lock lock;

	public Producer(Queue<UnitOfWork> buffer, String name, Integer maxSize, Lock lock) {
		this.buffer = buffer;
		this.name = name;
		this.maxSize = maxSize;
		this.lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			if (buffer.size() < maxSize) {
				buffer.add(new UnitOfWork());
				System.out.println("Produced new item by " + name + " and the buffer size is " + buffer.size());
			}
			lock.unlock();
		}
	}
}
```
```java 
package multithreading.addersubtractor.solutions.lock;

public class UnitOfWork {

}

```

```java 
package multithreading.addersubtractor.solutions.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	public static void main(String[] args) {
		Queue<UnitOfWork> buffer = new ConcurrentLinkedDeque<>();
		Lock lock = new ReentrantLock();
		int maxSize = 10;
		for (int i = 0; i <= 3; i++) {
			Runnable producer = new Producer(buffer, "P" + i, maxSize, lock);
			Runnable consumer = new Consumer(buffer, "C" + i, lock);
			new Thread(producer).start();
			new Thread(consumer).start();
		}
	}

}
```