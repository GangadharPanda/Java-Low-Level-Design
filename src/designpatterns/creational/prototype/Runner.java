package designpatterns.creational.prototype;

public class Runner {
	static void initializeRegistry(StudentRegistry registry) {
		Student stOfSept23Batch = new Student();
		stOfSept23Batch.setBatchName("Sept 23 Morning");
		stOfSept23Batch.setBatchInstructor("Deepak Mishra");

		IntelligentStudent stOfOct23Batch = new IntelligentStudent();
		stOfOct23Batch.setBatchName("Oct 23 Morning");
		stOfOct23Batch.setBatchInstructor("Deepak Mishra");

		registry.add("Sept23Morning", stOfSept23Batch);
		registry.add("Oct23Morning", stOfOct23Batch);
	}

	public static void main(String[] args) {
		StudentRegistry registry = new StudentRegistry();
		initializeRegistry(registry);

		Student gangadhar = registry.get("Sept23Morning").clone();

		gangadhar.setName("Gangadhar");
		gangadhar.setPsp(74);
		gangadhar.setUniversityName("NEO ");
		System.out.println(gangadhar);
		System.out.println(registry.get("Sept23Morning"));
		
		//-----------------------------------
		
		Student gangadharStudent = registry.get("Oct23Morning").clone();

		gangadharStudent.setName("Gangadhar");
		gangadharStudent.setPsp(74);
		gangadharStudent.setUniversityName("NEO ");
		System.out.println(gangadharStudent);
		System.out.println(registry.get("Oct23Morning"));
	}

}
