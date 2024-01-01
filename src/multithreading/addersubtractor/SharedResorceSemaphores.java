package multithreading.addersubtractor;

import java.util.concurrent.Semaphore;

public class SharedResorceSemaphores extends SharedResorce {
	Semaphore semaphore = new Semaphore(1);

	public SharedResorceSemaphores(int count) {
		super(count);
	}

	public void increment() {
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
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.decrement();
		semaphore.release();
	}
}
