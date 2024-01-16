package designpatterns.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

	private String name;
	private int age;
	private Double psp;
	private String universityName;

	// No body can create object calling this
	private Student(Builder builder) {
		this.name = builder.getName();
		this.age = builder.getAge();
		this.psp = builder.getPsp();
		this.universityName = builder.getUniversityName();
	}

	static class Builder {
		private String name;
		private int age;
		private Double psp;
		private String universityName;

		private Builder() {

		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public int getAge() {
			return age;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public Double getPsp() {
			return psp;
		}

		public Builder setPsp(Double psp) {
			this.psp = psp;
			return this;
		}

		public String getUniversityName() {
			return universityName;
		}

		public Builder setUniversityName(String universityName) {
			this.universityName = universityName;
			return this;
		}

		boolean isValid() {
			if (this.getAge() < 13) {
				return false;
			}
			return true;
		}

		public Student build() throws IllegalAccessException {
			if (!isValid()) {
				throw new IllegalAccessException("Please provide parameter");
			}
			return new Student(this);
		}

	}

	public static Builder getBuilder() {
		return new Builder();
	}
}
