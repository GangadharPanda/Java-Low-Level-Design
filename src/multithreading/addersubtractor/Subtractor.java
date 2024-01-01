package multithreading.addersubtractor;

public class Subtractor extends Thread {
	SharedResorce sharedResorce;

	public Subtractor(SharedResorce sharedResorce) {
		this.sharedResorce = sharedResorce;
	}

	public void run() {
		System.out.println("--------- Subtractor started ----------");
		for (int i = 0; i < 100; i++) {
			sharedResorce.decrement();
		}
		System.out.println("--------- Subtractor Completed ----------");
	}
}
