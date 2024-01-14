package designpatterns.singleton;

public class DBConnectionEarlyInitialization {

	private static DBConnectionEarlyInitialization dbConnection = new DBConnectionEarlyInitialization();;

	private DBConnectionEarlyInitialization() {
	}

	public static DBConnectionEarlyInitialization getInstance() {
		return dbConnection;
	}
}
