package multithreading.problems;

import java.util.concurrent.locks.Lock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedResourceWithLock extends SharedResource {
	public SharedResourceWithLock(int value, Lock lock) {
		super(value);
		this.lock = lock;
	}

	private volatile Lock lock;

	public void increment() {
		lock.lock();
		System.out.println("Current Value is " + getValue());
		setValue(getValue() + 1);
		System.out.println("Value is updated to " + getValue());
		lock.unlock();
	}
}
