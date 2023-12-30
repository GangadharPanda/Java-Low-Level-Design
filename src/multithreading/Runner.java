package multithreading;

public class Runner {

	public static void main(String[] args) {
		SharedResorce sharedResource = new SharedResorce(0);
		Thread adder = new Adder(sharedResource);
		Thread subtractor = new Subtractor(sharedResource);

//		adder.start();
//		subtractor.start();
//		try {
//			adder.join();
//			subtractor.join();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		sharedResource.show();
//		// -------------- Solution with Synchronized keyword
//
//		System.out.println("-- Solution with Synchronized keyword  --");
//
//		sharedResource = new SharedResorceWithSynchronized(0);
//		adder = new Adder(sharedResource);
//		subtractor = new Subtractor(sharedResource);
//
//		adder.start();
//		subtractor.start();
//		try {
//			adder.join();
//			subtractor.join();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		sharedResource.show();

		// ---- Solution with Reentrant Lock -

//		System.out.println("-- Solution with Reentrant Lock  --");
//
//		sharedResource = new SharedResorceWithReentrantLock(0);
//		adder = new Adder(sharedResource);
//		subtractor = new Subtractor(sharedResource);
//
//		adder.start();
//		subtractor.start();
//		try {
//			adder.join();
//			subtractor.join();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		sharedResource.show();

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

		sharedResource.show();

	}

}
