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
