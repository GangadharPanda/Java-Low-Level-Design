#Singleton Design Pattern

 ![alt text](Creational Design Pattern.PNG "Image" )
   source : refactoring.guru



Singleton classes in Java are useful when you need only one class instance to control shared resources or ensure consistent behavior. They manage database connections, configuration settings, and thread pools.

This is particularly helpful for managing resources such as database connections, logging systems, or configuration settings. Singleton ensures that these resources are easily accessible and consistent across the application. 

To implement a singleton pattern, we have different approaches, but all of them have the following common concepts.

1. private constructor to restrict instantiation of the class from other classes.
2. private static variable of the same class that is the only instance of the class.
3. public static method that returns the instance of the class, this is the global access point for the outer world to get the instance of the singleton class.


eg:

```java
package designpatterns.singleton;

public class DBConnection {

	private static DBConnection dbConnection = null;

	private DBConnection() {
	}

	public static DBConnection getInstance() {
		if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
		return dbConnection;
	}
}

```

The above implementation is not THREAD SAFE.

---------------------------------------------------------------------------------

There are two ways to handle multi-threading in singleton class

1. Eager initialization
2. Using Lock/synchronization 
3. Double check locking 

---------------------------------------------------------------------------------

1. Eager initialization 

This is the simplest approach to create a singleton class that supports multi-threading, In this approach we create the object straight away. So when multiple threads try to call the getInstance() method it simply returns the already created instance.

```java 
package designpatterns.singleton;

public class DBConnectionEarlyInitialization {

	private static DBConnectionEarlyInitialization dbConnection = new DBConnectionEarlyInitialization();;

	private DBConnectionEarlyInitialization() {
	}

	public static DBConnectionEarlyInitialization getInstance() {
		return dbConnection;
	}
}
```

Disadvantage of this approach is 

1. Even if no one is calling getInstance() method , it is creating one.
2. Suppose I want to getInstance(Environment env) , i.e based on the environment , I can not do that 

i.e In above getInstance() method , even if we pass a parameter. We won't be able to use that parameter because we have already created the object.


```java
package designpatterns.singleton;

public class DBConnectionWithSynchronization {

	private static DBConnectionWithSynchronization dbConnection = null;

	private DBConnectionWithSynchronization() {
	}

	public static synchronized DBConnectionWithSynchronization getInstance() {
		if (dbConnection == null) {
            dbConnection = new DBConnectionWithSynchronization();
        }
		return dbConnection;
	}
} 
```
Disadvantage of this approach is 

1. Once we have created the instance , all other threads should not wait . But in this case all threads will have to wait to call getInstance() method , even if we have created the instance ,which is not performant.



```java 
package designpatterns.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBConnectionWithDoubleCheckLocking {

	private static DBConnectionWithDoubleCheckLocking dbConnection = null;
	private static Lock lock = new ReentrantLock();

	private DBConnectionWithDoubleCheckLocking() {
	}

	public static synchronized DBConnectionWithDoubleCheckLocking getInstance() {

		/**
		 * Suppose T1 and T2 are two threads calling the getInstance method both finds
		 * dbConnection == null and enters the if condition.
		 * Now, suppose T1 acquires the lock, creates the instance and release the lock also returns the instance.
		 * T2 which was waiting for locks, acquires the lock and creates the instance so
		 * both creates two different instances.
		 * 
		 * To solve issue , if we add another check of instance == null , so that T2
		 * checks if some other thread has already created the instance. In that case T2 simply
		 * returns instance.
		 */
		if (dbConnection == null) {
			lock.lock();
			if (dbConnection == null) {
                dbConnection = new DBConnectionWithDoubleCheckLocking();
            }
			lock.unlock();
		}
		return dbConnection;
	}
}

```


Double check singleton pattern

However this implementation is buggy if we forget to declare the variable instance as volatile .
Without volatile we don't have happens before link between synchronize write and read. 

Volatile ensure that multiple threads read the correct instance value.
Also make object immutable.











