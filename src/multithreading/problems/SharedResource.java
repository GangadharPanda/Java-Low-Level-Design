package multithreading.problems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SharedResource {
	private volatile int value = 0;

	public synchronized void increment() {
		System.out.println("Current Value is " + value);
		value++;
		System.out.println("Value is updated to " + value);
	}
}
