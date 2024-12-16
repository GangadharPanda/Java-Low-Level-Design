# When do we need Prototype Design Pattern

Prototype is a creational design pattern that lets us copy existing objects without making your code dependent on their classes.


   
   ![alt text](prototype.png "Image")
   source : refactoring.guru
   
   Say we have an object, and we want to create an exact copy of it. How can we do it? First, we have to create a new object of the same class. Then we have to go through all the fields of the original object and copy their values over to the new object.

Nice! But there’s a catch. Not all objects can be copied that way because some of the object’s fields <b>may be private</b> and not visible from outside of the object itself.

  ![alt text](prototype-comic-1-en.png "Image")
  
Copying an object “from the outside” isn’t always possible. 
   source : refactoring.guru
   
---

There’s one more problem with the direct approach. Since we have to know the object’s class to create a duplicate, the code becomes dependent on that class.

```java 

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

```

```java

package designpatterns.creational.prototype;

public interface Prototype<T> {

    T clone();

}

```

With inheritance

```java
package designpatterns.creational.prototype;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IntelligentStudent extends Student {
    private int iq;

    @Override
    public IntelligentStudent clone() {
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
```

```java
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

```
With Registry

```java
package designpatterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistry {
    private Map<String, Student> registry = new HashMap<>();

    public Student get(String key) {
        return registry.get(key);
    }

    public void add(String key, Student obj) {
        registry.put(key, obj);
    }
}

```