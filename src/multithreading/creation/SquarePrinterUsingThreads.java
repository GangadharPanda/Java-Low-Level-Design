package multithreading.creation;

public class SquarePrinterUsingThreads extends Thread {

	private Integer number;
	private String name;

	SquarePrinterUsingThreads(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(
				"Square of the number " + number + " = " + (number * number) + " calculated By Thread " + name);
	}

}
