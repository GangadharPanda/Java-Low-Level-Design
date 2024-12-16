package designpatterns.creational.singleton;

public class SingletonWithoutLockRunner {

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(DBConnection.getInstance());
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("T2 's " + DBConnection.getInstance());
			}
		});

		t1.start();
		t2.start();
	}
	
	/**
	 * 
	 * designpatterns.creational.singleton.DBConnection@37492b9b
     * T2 's designpatterns.creational.singleton.DBConnection@3f0cd36f
	 * 
	 */

}
