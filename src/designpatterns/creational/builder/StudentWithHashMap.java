package designpatterns.creational.builder;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentWithHashMap {

	private String name;
	private int age;
	private Double psp;
	private String universityName;

	StudentWithHashMap(Map<String, Object> requestParams) {
		int age = (int) requestParams.get("age");

		if (age <= 14)
			throw new IllegalArgumentException("This must be more than 14 ");
		this.name = (String) requestParams.get("name");
		this.age = age;
		if (requestParams.containsKey("psp")) {
			this.psp = (double) requestParams.get("psp");
		}
		this.universityName = (String) requestParams.get("universityName");
	}
}
