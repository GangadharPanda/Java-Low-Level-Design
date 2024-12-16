package designpatterns.creational.singleton;

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
