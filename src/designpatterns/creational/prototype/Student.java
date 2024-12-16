package designpatterns.creational.prototype;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Student implements Prototype<Student> {

	String name;
	int age;
	double psp;
	String universityName;
	String batchName;
	String batchInstructor;
	Double batchAvgPSP;

	Student(Student st) {
		this.name = st.name;
		this.age = st.age;
		this.psp = st.psp;
		this.universityName = st.universityName;
		this.batchName = st.batchName;
		this.batchInstructor = st.batchInstructor;
		this.batchAvgPSP = st.batchAvgPSP;
	}

	@Override
	public Student clone() {
		return new Student(this);
	}

}
