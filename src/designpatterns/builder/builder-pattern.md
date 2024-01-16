#When do we need Builder Design Pattern

##Large Number of Parameters

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
       
##Validation of the parameters before creating an Object:

   When we want to validate the parameters before creating an object.
   
   eg if we want to validate the age of a Student >= 14 , to start graduation course 
   
   if we try using 
   
   ```java 
   Student st = new Student("XYZ", age : 13);
   ```
   The object is already created , so we will need to check this inside the constructor itself.
   
   ```java
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
	 package designpatterns.builder;
	
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
	   package designpatterns.builder;
	
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
    
   
   
  
  
   
    
    

      
  
      
 
   




