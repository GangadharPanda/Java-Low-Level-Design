# Java Synchronization

When multiple threads access shared resources, it can lead to synchronization issues. Let’s explore the need for synchronization, the issues that arise without it, and the solutions available in Java.

# What is the issue if we don't use synchronization?
```java 
package multithreading.addersubtractor.solutions.synchronizedsolution;

import java.util.Queue;

public class Producer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Integer maxSize;

	public Producer(Queue<UnitOfWork> buffer, String name, Integer maxSize) {
		this.buffer = buffer;
		this.name = name;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (buffer) {
				if (buffer.size() < maxSize) {
					buffer.add(new UnitOfWork());
					System.out.println("Produced new item by " + name + " and the buffer size is " + buffer.size());
				}
			}
		}
	}
}
```

```java
package multithreading.addersubtractor.solutions.synchronizedsolution;

import java.util.Queue;

public class Consumer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;

	public Consumer(Queue<UnitOfWork> buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (buffer) {
				if (buffer.size() > 0) {
					buffer.remove();
					System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
				}
			}
		}
	}

}
```

```java
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
```
