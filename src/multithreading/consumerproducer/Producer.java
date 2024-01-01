package multithreading.consumerproducer;

public class Producer implements Runnable {

	SharedBuffer buffer;

	public Producer(SharedBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			buffer.produce(i);
		}
	}
}
