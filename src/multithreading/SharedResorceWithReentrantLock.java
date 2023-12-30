package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResorceWithReentrantLock extends SharedResorce {
	Lock lock = new ReentrantLock();

	public SharedResorceWithReentrantLock(int count) {
		super(count);
	}

	public void increment() {
		lock.lock();//aquire the lock
		super.increment();
		lock.unlock();//release the lock
	}

	public void decrement() {
		lock.lock();//aquire the lock
		super.decrement();
		lock.unlock();//release the lock
	}
}
