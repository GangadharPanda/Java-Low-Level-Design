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
		 * 
		 * Suppose T1 and T2 are two threads calling the getInstance method both finds
		 * dbConnection == null and enters the if condition.
		 * Now, suppose T1 acquires the lock, creates the instance and release the lock also returns the instance.
		 * T2 which was waiting for locks, acquires the lock and creates the instance so
		 * both creates two different instances
		 * 
		 * To solve issue , if we add another check of instance == null , so that T2
		 * checks if some other thread has created the instance In that case T2 simply
		 * returns the already created instance
		 * 
		 * 
		 * 
		 */
		if (dbConnection == null) {
			lock.lock();
			if (dbConnection == null)
				dbConnection = new DBConnectionWithDoubleCheckLocking();
			lock.unlock();
		}
		return dbConnection;
	}
}
