package inheritance;

public class Dog extends Animal {
	static String hello() {
		Animal.hello();// Only way to call the parent class static method
		return "Hello Dog";
	}
	
	

	void myMethod() {
		super.myMethod();
		String str = "Dog class myMethod ";
		System.out.println("Dog class myMethod ");
	}
}
