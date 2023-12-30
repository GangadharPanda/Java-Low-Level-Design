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
