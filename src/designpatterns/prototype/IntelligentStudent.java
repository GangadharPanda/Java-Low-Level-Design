package designpatterns.prototype;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IntelligentStudent extends Student {
	private int iq;

	@Override
	public IntelligentStudent copy() {
		IntelligentStudent student = new IntelligentStudent();
		student.setName(this.getName());
		student.setAge(this.getAge());
		student.setPsp(this.getPsp());
		student.setUniversityName(this.getUniversityName());
		student.setBatchInstructor(this.getBatchInstructor());
		student.setBatchAvgPSP(this.getBatchAvgPSP());
		student.setBatchName(this.getBatchName());
		student.setIq(this.getIq());
		return student;
	}

	IntelligentStudent(IntelligentStudent intelligentStudent) {
		super(intelligentStudent);
		this.iq = intelligentStudent.iq;
	}
}