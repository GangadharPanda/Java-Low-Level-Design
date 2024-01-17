package designpatterns.builder;

public class Runner {

	public static void main(String[] args) {
		try {
			Student st = Student.getBuilder()
					.setName("Gangadhar")
					.setAge(33)
					.setPsp(74d)
					.setUniversityName("NEO University")
					.build();
			
			System.out.println(st.getName() + " " + st.getAge());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
