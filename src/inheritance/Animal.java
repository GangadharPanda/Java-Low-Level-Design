package inheritance;

public class Animal {
	private int noOfLegs = 0;

	public int getNoOfLegs() {
		return noOfLegs;
	}

	public void setNoOfLegs(int noOfLegs) {
		this.noOfLegs = noOfLegs;
	}

	private Animal(int x) {
		noOfLegs = x;
	}

	public Animal() {

	}

	static String hello() {
		return "Hello Animal";
	}
	
	void myMethod() {
		System.out.println("Animal class myMethod ");
	}

}
