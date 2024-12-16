# Singleton Design Pattern
Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.

![alt text](Singleton.png "Image" )
Source : refactoring.guru

### Ensure that a class has just a single instance. Why would anyone want to control how many instances a class has? 
The most common reason for this is to control access to some shared resource—for example, a database or a file.

Here’s how it works: imagine that you created an object, but after a while decided to create a new one. 
Instead of receiving a fresh object, you’ll get the one you already created.

Note that this behavior is impossible to implement with a regular constructor since a constructor call must always return a new object by design.


### Provide a global access point to that instance. 
Remember those global variables that you (all right, me) used to store some essential objects?
While they’re very handy, they’re also very unsafe since any code can potentially overwrite the contents of those variables and crash the app.

Just like a global variable, the Singleton pattern lets you access some object from anywhere in the program. 
However, it also protects that instance from being overwritten by other code.

There’s another side to this problem: you don’t want the code that solves problem
#1 to be scattered all over your program. It’s much better to have it within one class, especially if the rest of your code already depends on it.

Singleton classes in Java are useful when you need only one class instance to control shared resources or ensure consistent behavior. 
They manage database connections, configuration settings, and thread pools.

This is particularly helpful for managing resources such as database connections, logging systems, or configuration settings. 
Singleton ensures that these resources are easily accessible and consistent across the application. 

## Solution
All implementations of the Singleton have these two steps in common:

Make the default constructor private, to prevent other objects from using the new operator with the Singleton class.
Create a static creation method that acts as a constructor. Under the hood, this method calls the private constructor to create an object and saves it in a static field. All following calls to this method return the cached object.


eg:

```java
package designpatterns.singleton;

public class DBConnection {

   private static DBConnection INSTANCE = null;

   private DBConnection() {
   }

   public static DBConnection getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new DBConnection();
      }
      return INSTANCE;
   }
}

```

The above implementation is not THREAD SAFE.

---------------------------------------------------------------------------------
There are two ways to handle multi-threading in singleton class

1. Eager initialization
2. Using Lock/synchronization  -- Method level locking 
3. Double check locking -- Critical section locking

---------------------------------------------------------------------------------

1. Eager initialization 

This is the simplest approach to create a singleton class that supports multi-threading, In this approach we create the object straight away. So when multiple threads try to call the getInstance() method it simply returns the already created instance.

```java 
package designpatterns.singleton;

public class DBConnectionEarlyInitialization {

   private static DBConnectionEarlyInitialization INSTANCE = new DBConnectionEarlyInitialization();

   private DBConnectionEarlyInitialization() {
   }

   public static DBConnectionEarlyInitialization getInstance() {
      return INSTANCE;
   }
}
```

Disadvantage of this approach is 

1. The object is created at the time of class loading , so the object is created irrespective of it's needed or not.
2. As the instance is created at the time of class loading , it can not have the dynamic parameters

i.e In above getInstance() method , even if we pass a parameter. We won't be able to use that parameter because we have already created the object.

2. Using Lock/synchronization  -- Method level locking


```java
package designpatterns.singleton;

public class DBConnectionWithSynchronization {

   private static DBConnectionWithSynchronization INSTANCE = null;

   private DBConnectionWithSynchronization() {
   }

   public static synchronized DBConnectionWithSynchronization getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new DBConnectionWithSynchronization();
      }
      return INSTANCE;
   }
} 
```

Disadvantage of this approach is 
Once we have created the instance , all other threads should not wait . 
But in this case all threads will have to wait to call getInstance() method 
, even if we have created the instance ,which is not performant.

3. Double check locking -- Critical section locking + Double Check

```java 
package designpatterns.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBConnectionWithDoubleCheckLocking {

	private static volatile DBConnectionWithDoubleCheckLocking INSTANCE = null;
	private static Lock lock = new ReentrantLock();

	private DBConnectionWithDoubleCheckLocking() {
	}

	public static DBConnectionWithDoubleCheckLocking getInstance() {

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
		if (INSTANCE == null) { // First Check, before entering to critical section
			lock.lock(); 
            // Above line can simply be replaced with synchronized(DBConnectionWithDoubleCheckLocking.class)
           // If we don't want to use locking mechanism
			if (INSTANCE == null) { // Second Check, inside the critical section
               INSTANCE = new DBConnectionWithDoubleCheckLocking();
            }
			lock.unlock();
		}
		return INSTANCE;
	}
}

```

## NOTE 
However this implementation is buggy if we forget to declare the variable instance as volatile .
Without volatile we don't have happens before link between synchronize write and read. 

Volatile ensure that multiple threads read the correct instance value.
Also make object immutable.

----------------------------------------------------

## Proc & Cons

![alt text](ProsCons.png "Image" )
Source : refactoring.guru











