package multithreading.consumerproducer;

import java.util.ArrayList;
import java.util.List;

public class SharedBuffer {
	final int maxSize = 10;

	List<Integer> buffer = new ArrayList<>(maxSize);

	List<Integer> getBuffer() {
		return buffer;
	}

	void produce(Integer val) {
		if (buffer.size() < 10) {
			buffer.add(val);
			System.out.println("PRODUCING  " + val);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Could not produce  " + val);
		}

	}

	void consume() {
		if (buffer.size() > 0) {
			Integer x = buffer.remove(buffer.size() - 1);
			System.out.println("CONSUMED " + x);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
