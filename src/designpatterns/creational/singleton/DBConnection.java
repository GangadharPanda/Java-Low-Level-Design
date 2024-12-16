package designpatterns.creational.singleton;

public class DBConnection {

	private static volatile DBConnection dbConnection = null;

	private DBConnection() {
	}

	public static DBConnection getInstance() {
		if (dbConnection == null)
			dbConnection = new DBConnection();
		return dbConnection;
	}
}
