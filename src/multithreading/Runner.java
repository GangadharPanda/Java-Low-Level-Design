package multithreading;

public class Runner {

	public static void main(String[] args) {
		SharedResorce sharedResource = new SharedResorce(0);
		Thread adder = new Adder(sharedResource);
		Thread subtractor = new Subtractor(sharedResource);

		/*
		 * When we try to use multiple threads on a share object , if the shared
		 * resource is not synchronized it can cause inconsistent results.
		 * 
		 * Adder thread increments the shared variable 100 times , and Subtractor thread
		 * decrements the value 100 times.
		 * 
		 * Intentionally , the threads are sleeping for 10 ms , to ensure another both
		 * of these threads gets the chance to execute the shared variable
		 * 
		 */

		adder.start();
		subtractor.start();
		try {
			adder.join();
			subtractor.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// The correct value of the output should be 0 , but it's non 0
		System.out.println(sharedResource.getCount());

		// -------------- Solution with Synchronized keyword

		System.out.println("-- Solution with Synchronized keyword  --");

		sharedResource = new SharedResorceWithSynchronized(0);
		adder = new Adder(sharedResource);
		subtractor = new Subtractor(sharedResource);

		adder.start();
		subtractor.start();
		try {
			adder.join();
			subtractor.join();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(sharedResource.getCount());
		// ---- Solution with Reentrant Lock -

		System.out.println("-- Solution with Reentrant Lock  --");

		sharedResource = new SharedResorceWithReentrantLock(0);
		adder = new Adder(sharedResource);
		subtractor = new Subtractor(sharedResource);

		adder.start();
		subtractor.start();
		try {
			adder.join();
			subtractor.join();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(sharedResource.getCount());

		// ---- Solution with Reentrant Lock -

		System.out.println("-- Solution with Semaphores  --");

		sharedResource = new SharedResorceSemaphores(0);
		adder = new Adder(sharedResource);
		subtractor = new Subtractor(sharedResource);

		adder.start();
		subtractor.start();
		try {
			adder.join();
			subtractor.join();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(sharedResource.getCount());

	}

}
