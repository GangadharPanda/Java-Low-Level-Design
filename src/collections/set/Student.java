package collections.set;

import java.util.Objects;

public class Student implements Comparable<Student> {

	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Student o) {

		System.out.println("this = " + this);// THIS is the element which is to be inserted into the map
		System.out.println("Parameter = " + o);// Incoming parameter is the elements which are already saved into the
												// tree

		int x = this.name.compareTo(o.name);

		System.out.println("The value returned from compareTo method is " + x);
		
		// compare(Object obj1, Object obj2 ) == obj.compareTo(obj2)
		return x;
	}

//	@Override
//	public int compareTo(Student o) {
//		if(this.id > o.id) return -1;
//		if(this.id < o.id) return 1 ;
//		return 0;
//	}

	@Override
	public String toString() {
		return "Student [name =" + name + ", id =" + id + "]";
	}

}
