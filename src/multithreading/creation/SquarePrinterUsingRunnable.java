package multithreading.creation;

public class SquarePrinterUsingRunnable implements Runnable {
	private Integer number;
	private String name;

	SquarePrinterUsingRunnable(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(
				"Square of the number " + number + " = " + (number * number) + " calculated By Runnable " + name);

	}
}
