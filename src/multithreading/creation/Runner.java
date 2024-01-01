package multithreading.creation;

import java.util.List;

public class Runner {

	public static void main(String[] args) {
		List<Integer> nums = List.of(1, 2, 3, 4, 5);

		for (Integer num : nums) {
			Thread squarePrinter = new SquarePrinterUsingThreads(num, "Square Printer");
			squarePrinter.start();
		}
		
		//Output 
		
		/**
		 * 	Square of the number 1 = 1 calculated By Square Printer thread
		   	Square of the number 2 = 4 calculated By Square Printer thread
			Square of the number 3 = 9 calculated By Square Printer thread
			Square of the number 4 = 16 calculated By Square Printer thread
			Square of the number 5 = 25 calculated By Square Printer thread
		 * 
		 */
		// Using Runnable 
		for (Integer num : nums) {
			Runnable squarePrinterRunnable = new SquarePrinterUsingRunnable(num, "Square Printer");
			Thread thread = new Thread(squarePrinterRunnable);
			thread.start();
		}
		
		
	}

}
