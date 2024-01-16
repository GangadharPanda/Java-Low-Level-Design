package designpatterns.builder;

public class RunnerWithHelper {

	public static void main(String[] args) {
		StudentHelper stHelper = StudentHelper.getStudentHelper();
		stHelper.setName("Gangadhar");
		stHelper.setAge(33);
		stHelper.setPsp(74d);
		stHelper.setUniversityName("Neo University");

		StudentWithHelper st = new StudentWithHelper(stHelper);
		System.out.println(st.getName() + " is of age " + st.getAge());
	}
}
