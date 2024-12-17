# When do we need Builder Design Pattern


## Large Number of Parameters

   When a class has a large number of optional parameters, the constructor may become difficult to use, and calling it with many parameters in a specific order can be error-prone.
   
   Suppose , a class have n parameters , it will lead to create possibly 2^n constructor.
   
   eg : if parameters are - name and age 
   Possible constructors 
   
   ```java
   
      new Student();
      new Student(name);
      new Student(age);
      new Student(name,age);
   ```
      
  Sometimes it is not even possible to create required constructor due to language restrictions 
   
   
  eg :
  
  ```java 
      new Student(name, age);
      new Student(name psp);
  ```
      
This will lead to compile time error.

---------------------------------------------------------------------------------------------------------
       
## Validation of the parameters before creating an Object:

   When we want to validate the parameters before creating an object.
   
   eg if we want to validate the age of a Student >= 14 , to start graduation course 
   
   if we try using 
   
   ```java 
   Student st = new Student("XYZ", age : 13);
   ```
   The object is already created , so we will need to check this inside the constructor itself.

   ```java
package designpatterns.creational.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private String name;
    private int age;
    private Double psp;
    private String universityName;

    Student(String name, int age) {
        if (age <= 14)
            throw new IllegalArgumentException("This must be more than 14 ");
        this.name = name;
        this.age = age;
    }
}
   ```
   
   This solves the problem but how can we handle the issue of numerous constructor?
   
   ### Solution 1 
     
   Use a single constructor with parameter of type HashMap
   i.e

   ```java 
package designpatterns.creational.builder;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private String name;
    private int age;
    private Double psp;
    private String universityName;

    Student(Map<String, Object> requestParams) {
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
   ```
   
  The Runner code is below

  ```java
package designpatterns.creational.builder;

import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        Map<String, Object> requestParams = new HashMap<>();

        requestParams.put("name", "Gangadhar");
        requestParams.put("age", 33);
        Student st = new Student(requestParams);
        System.out.println(st.getName() + " is of age " + st.getAge());
    }
}

```
  
  There are two issues with this approach 
  
  1. As we are inserting the keys manually , there is no compile time safety is available.
    i. e I can type Name in place of name and we will know this mistake at runtime .
    
  2. As the value is of type Object , each parameter has to be type casted carefully.
    
    suppose I write 
    requestParams.put("age", "33");
    
   This is going to break at runtime the error
   
   ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer.
   

---------------------------------------------------------------------------------------------------------
 
 # How can we fix this issue ??
 
 What if create a helper Class , which has all the attribute as original class ?

  ```java

package designpatterns.creational.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentHelper {
    private String name;
    private int age;
    private Double psp;
    private String universityName;
}

```
 
 Actual Student Class

 ```java

package designpatterns.creational.builder;

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


```
 
 Runner Class

 ```java

package designpatterns.creational.builder;

public class RunnerWithHelper {

    public static void main(String[] args) {
        StudentHelper stHelper = new StudentHelper();
        stHelper.setName("Gangadhar");
        stHelper.setAge(33);
        stHelper.setPsp(74d);
        stHelper.setUniversityName("Neo University");

        StudentWithHelper st = new StudentWithHelper(stHelper);
        System.out.println(st.getName() + " is of age " + st.getAge());
    }
}


```
---

Above code solves all our problems , but introduces a StudentHelper class which is of no use of its own but just there to help the Student Object creation

So we are going to make it a inner class of the Student class

```java

package designpatterns.creational.builder;

import lombok.Getter;

@Getter
public class Student {

    private String name;
    private int age;
    private Double psp;
    private String universityName;

    // NOTE 1 : Student class objects are immutable , so no setters are available here.

    // NOTE 2 : Only way to create Student Object is calling getBuilder() method
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

        // NOTE 3 : Builder object does not have any existence without the Student Object
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

        // NOTE 4 : This is the only way to create Student Object
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


```



