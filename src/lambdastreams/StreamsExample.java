package lambdastreams;

import java.util.List;
import java.util.Optional;

public class StreamsExample {

	private static List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	private static List<String> names = List.of("Tantia Tope", "Rani Lakshmibai", "Mangal Pandey", "Nana Sahib");

	public static void main(String[] args) {
		Question1();
		Question2();
		Question3();
		Question4();
		Question5();
		Question6();
		Question7();
		Question8();
		Question9();
		Question10();
		Question11();
		Question12();
		Question13();
	}

	private static void Question1() {
		System.out.println(" Q1 . Iterate over it and print the square of each ");
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		numbers
				.stream()
				.map(val -> val * val)
				.forEach(System.out::println);
	}
	
	private static void Question2() {
		System.out.println(" Q2 . Iterate over it and print every even number");
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		numbers
				.stream()
				.filter(val -> val % 2 == 0)
				.forEach(System.out::println);
	}
	
	private static void Question3() {
		System.out.println(" Q3 . Find all the even numbers. Then, iterate over the even numbers and print square of each number");
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		numbers
				.stream()
				.filter(val -> val % 2 == 0)
				.map(val-> val * val)
				.forEach(System.out::println);
	}
	
	private static void Question4() {
		System.out.println(" Q4 . For a list of strings, return a list of the length of each string..");
		System.out.println("------Before Operation ------");
		System.out.println(names);
		System.out.println("------After Operation ------");
		names
				.stream()
				.map(val-> val.length())
				.forEach(System.out::println);
	}
	
	private static void Question5() {
		System.out.println(" Q5 . filter out the strings which do not start with the letter T, then uppercase the remaining strings and then sort them");
		System.out.println("------Before Operation ------");
		System.out.println(names);
		System.out.println("------After Operation ------");
		names
				.stream()
				.filter(val-> !val.startsWith("T"))
				.map(val -> val.toUpperCase())
				.sorted()
				.forEach(System.out::println);
	}
	
	private static void Question6() {
		System.out.println(" Q6 . Find the sum of all the numbers");
		
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------"); 
		
		int x = numbers
		.stream()
		.reduce(0, (currentSum , currentElement)-> currentSum + currentElement);
		System.out.println(x);
	}

	private static void Question7() {
		System.out.println(" Q7 . Find the sum of the squares of all the numbers.");
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		
		int x = numbers
		.stream()
		.map(val-> val * val)
		.reduce(0, (currentSum , currentElement)-> currentSum + currentElement);
		System.out.println(x);
	}
	
	private static void Question8() {
		System.out.println(" Q8 . Find the sum of the squares of all the even numbers.");
		
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		
		int x = numbers
		.stream()
		.filter(val -> val % 2 == 0)
		.map(val-> val * val)
		.reduce(0, (currentSum , currentElement)-> currentSum + currentElement);
		System.out.println(x);
	}
	
	private static void Question9() {
		System.out.println(" Q9 . Find the max of all the numbers.");
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		
		Optional<Integer> x = numbers
		.stream()
		.max(Integer::compare);
		System.out.println(x.get());
	}
	
	
	private static void Question10() {
		System.out.println(" Q10 . Find the distinct numbers in the list");
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5);
		
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------"); 
		
		numbers = numbers
		.stream()
		.distinct().toList();
		System.out.println(numbers);
	}
	
	private static void Question11() {
		// Calculate the average using streams
		System.out.println(" Q11 . Find the average of all the numbers in the list");
		System.out.println("------Before Operation ------");
		System.out.println(numbers);
		System.out.println("------After Operation ------");
		
		double average = numbers.stream()
		                    .mapToDouble(Integer::doubleValue) // Convert to double for average calculation
		                    .average()
		                    .orElse(0.0); // Handle empty list case

		System.out.println("The average is: " + average);
		
		
		/*
		 * 
		 * in this specific case, mapToDouble might seem unnecessary. Here's the
		 * breakdown of why it's used in the code even though it might not be strictly
		 * required:
		 * 
		 * Strict requirement:
		 * 
		 * The average() method in Java streams expects a stream of double values. Since
		 * your list contains Integer values, you need to convert them to double before
		 * applying average().
		 * 
		 */
	}
	
	private static void Question12() {
		System.out.println(" Q12 . find the average length of all the strings");
		
		System.out.println("------Before Operation ------");
		System.out.println(names);
		System.out.println("------After Operation ------");
		
		double average = names.stream()
							.map(val -> val.length())
		                    .mapToDouble(Integer::doubleValue) // Convert to double for average calculation
		                    .average()
		                    .orElse(0.0); // Handle empty list case

		System.out.println("The average is: " + average);
		
		
		/*
		 * 
		 * in this specific case, mapToDouble might seem unnecessary. Here's the
		 * breakdown of why it's used in the code even though it might not be strictly
		 * required:
		 * 
		 * Strict requirement:
		 * 
		 * The average() method in Java streams expects a stream of double values. Since
		 * your list contains Integer values, you need to convert them to double before
		 * applying average().
		 * 
		 */
	}
	
	private static void Question13() {
		//
		System.out.println(" Q13 .Find the first string that is longer than 10 characters.");
		System.out.println("------Before Operation ------");
		System.out.println(names);
		System.out.println("------After Operation ------");
		
		Optional<String> name = names
				.stream()
				.filter(val-> val.length() > 10)
				.findFirst();
		
		System.out.println(name.get());
	}
}
