# Why do we need Semaphores?

Both the the previous options using Lock and using synchronized keyword leads to a major problem of allowing only one thread 
at a time in the critical section. But we want 

n number of thread can simultaneously produce (i.e number of empty slots ) number of producer can produce at a time ,
also at a time multiple threads can consume.

Suppose we have , 10 plates . Initially all 10 plates can be filled with food . i.e 10 waiter can serve into 10 plates 
but at that time no one can consume from the plate before the waiter complete serving .
 


# How Does Semaphore works ?


```java
 Semaphore forProducer = new Semaphore(10);// Means 10 producer can produce at a time 
 Semaphore forConsumer = new Semaphore(0);//No Consumer can consume
 ```
 
 
 Once a producer starts producing , it will aquire producer lock by forProducer.acquire();
 // it will decrease available lock by 1 in forProducer
 
 Once a producer completes producing , it will signal consumer by calling forConsumer.release() 
 // It will increase available forConsumer lock by 1
 
 Once a Consumer starts consuming , it will aquire forConsumer lock by forConsumer.acquire()
 // it will decrease available lock by 1 in forConsumer
 
 once a consumer completes consuming , it will signal producer by calling forProducer.release()
 
 // It will increase available forConsumer lock by 1

```java 

package multithreading.addersubtractor.solutions.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Integer maxSize;

	private Semaphore forProducer;
	private Semaphore forConsumer;

	public Producer(Queue<UnitOfWork> buffer, String name, Integer maxSize, Semaphore forProducer,
			Semaphore forConsumer) {
		super();
		this.buffer = buffer;
		this.name = name;
		this.maxSize = maxSize;
		this.forProducer = forProducer;
		this.forConsumer = forConsumer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				forProducer.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buffer.add(new UnitOfWork());
			System.out.println("Produced new item by " + name + " and the buffer size is " + buffer.size());
			forConsumer.release();
		}
	}
}
```

```java 

package multithreading.addersubtractor.solutions.semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

	private Queue<UnitOfWork> buffer;
	private String name;
	private Semaphore forProducer;
	private Semaphore forConsumer;

	public Consumer(Queue<UnitOfWork> buffer, String name, Semaphore forProducer, Semaphore forConsumer) {
		super();
		this.buffer = buffer;
		this.name = name;
		this.forProducer = forProducer;
		this.forConsumer = forConsumer;
	}

	@Override
	public void run() {
		while (true) {
			try {
				forConsumer.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buffer.remove();
			System.out.println("Consumed item by " + name + " and the buffer size is " + buffer.size());
			forProducer.release();
		}
	}

}
```

```java 

package multithreading.addersubtractor.solutions.semaphore;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

public class Runner {

	public static void main(String[] args) {
		Queue<UnitOfWork> buffer = new ConcurrentLinkedDeque<>();
		int maxSize = 10;

		Semaphore forConsumer = new Semaphore(0);
		Semaphore forProducer = new Semaphore(maxSize);

		for (int i = 0; i <= 3; i++) {
			Runnable producer = new Producer(buffer, "P" + i, maxSize, forProducer, forConsumer);
			Runnable consumer = new Consumer(buffer, "C" + i, forProducer, forConsumer);
			new Thread(producer).start();
			new Thread(consumer).start();
		}
	}

}
```
