package multithreading;

public class SharedResorce {
	private int count = 0;

	public SharedResorce(int count) {
		this.count = count;
	}

	public void  increment() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}

	public void decrement() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count--;
	}

	public int getCount() {
		return count;
	}
}
