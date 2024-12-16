package designpatterns.creational.singleton;

public class DBConnectionEarlyInitialization {

	private static final DBConnectionEarlyInitialization INSTANCE = new DBConnectionEarlyInitialization();

	private DBConnectionEarlyInitialization() {
	}

	public static DBConnectionEarlyInitialization getInstance() {
		return INSTANCE;
	}
}
