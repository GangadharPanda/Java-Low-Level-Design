package multithreading;

public class Adder extends Thread{

	SharedResorce sharedResorce;

	public Adder(SharedResorce sharedResorce) {
		this.sharedResorce = sharedResorce;
	}
	
	public void run() {
		System.out.println("--------- Adder started ----------");
		
		for(int i = 0; i < 100; i++) {
			sharedResorce.increment();
		}
		System.out.println("--------- Adder Completed ----------");
	}

}
