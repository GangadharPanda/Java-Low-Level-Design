package multithreading.creation;

import java.util.concurrent.Callable;

public class SquareReturnerUsingCallable implements Callable<Integer> {

	private Integer number;

	SquareReturnerUsingCallable(Integer number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("Calculating the Square of " + number);
		return number * number;
	}

}
