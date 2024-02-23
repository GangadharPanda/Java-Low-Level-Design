package exceptionhandling;

import java.io.IOException;

public class ExceptionExample {

	public static void main(String[] args) {

//		runtimeException();
//		errorHandler();// This will fail a run time  though
//		try {
//			checkedException();// being a checkedException , This Line needs special handling
//		} catch (IOException e) {
//			System.out.println("IOException --- ");
//		}

		int x = 100;
		
		try {
			if (x == 100) {
				throw new NoDataFoundException(101, "No Data Found");
			}
		} catch (NoDataFoundException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		}
	}

	static void runtimeException() throws RuntimeException {
		System.out.println("runtimeException");
		throw new RuntimeException("Throwing Runtime Exception ");
	}

	static void errorHandler() throws Error {
		System.out.println("errorHandler");
		throw new Error("Throwing Error ");
	}

	static void checkedException() throws IOException {
		System.out.println("checkedException");
		throw new IOException("Throwing checkedException ");
	}

	/*
	 * 
	 * NOTE : we can throw all the exception, but only checked exception needs
	 * handling at compile time
	 */

}
