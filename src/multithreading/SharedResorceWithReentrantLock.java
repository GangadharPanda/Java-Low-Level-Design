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
		lock.lock();
		super.increment();
		lock.unlock();
	}

	public void decrement() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.lock();
		super.decrement();
		lock.unlock();
	}
}
