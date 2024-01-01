package multithreading.consumerproducer;

public class Consumer implements Runnable {

	SharedBuffer buffer;

	public Consumer(SharedBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			buffer.consume();
		}
	}

}
