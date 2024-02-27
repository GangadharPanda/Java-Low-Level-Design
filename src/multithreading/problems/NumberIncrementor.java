package multithreading.problems;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberIncrementor implements Runnable {

	SharedResource resource;

	@Override
	public void run() {
		resource.increment();
	}

}
