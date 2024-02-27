package multithreading.problems;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedResourceWithSemaphore extends SharedResource {
	public SharedResourceWithSemaphore(int value, Semaphore semaphore) {
		super(value);
		this.semaphore = semaphore;
	}

	private volatile Semaphore semaphore;

	public void increment() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("Current Value is " + getValue());
		setValue(getValue() + 1);
		System.out.println("Value is updated to " + getValue());
		semaphore.release();
	}
}
