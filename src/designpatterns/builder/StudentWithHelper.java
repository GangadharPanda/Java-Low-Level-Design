package designpatterns.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentWithHelper {

	private String name;
	private int age;
	private Double psp;
	private String universityName;

	StudentWithHelper(StudentHelper helper) {
		if (helper.getAge() <= 14)
			throw new IllegalArgumentException("This must be more than 14 ");
		this.name = helper.getName();
		this.age = helper.getAge();
		this.psp = helper.getPsp();
		this.universityName = helper.getUniversityName();
	}
}
