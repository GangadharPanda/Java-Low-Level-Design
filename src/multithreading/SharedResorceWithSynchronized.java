package multithreading;

public class SharedResorceWithSynchronized extends SharedResorce{

	public SharedResorceWithSynchronized(int count) {
		super(count);
	}

	public synchronized void increment() {
		super.increment();
	}

	public synchronized void decrement() {
		super.decrement();
	}
}
