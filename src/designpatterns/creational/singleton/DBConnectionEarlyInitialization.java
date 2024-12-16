package designpatterns.creational.singleton;

public class DBConnectionEarlyInitialization {

	private static final DBConnectionEarlyInitialization dbConnection = new DBConnectionEarlyInitialization();

	private DBConnectionEarlyInitialization() {
	}

	public static DBConnectionEarlyInitialization getInstance() {
		return dbConnection;
	}
}
