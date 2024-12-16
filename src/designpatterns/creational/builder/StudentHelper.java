package designpatterns.creational.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentHelper {
	private String name;
	private int age;
	private Double psp;
	private String universityName;

	private StudentHelper() {
	}

	public static StudentHelper getStudentHelper() {
		return new StudentHelper();
	}
}
