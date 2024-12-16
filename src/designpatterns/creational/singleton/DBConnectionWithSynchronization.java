package designpatterns.creational.singleton;

import java.io.Closeable;
import java.io.Serializable;

public class DBConnectionWithSynchronization implements Serializable, Cloneable {

    private static DBConnectionWithSynchronization INSTANCE = null;

    private DBConnectionWithSynchronization() {
    }

    public static synchronized DBConnectionWithSynchronization getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBConnectionWithSynchronization();
        }
        return INSTANCE;
    }

    // Ensures the same instance is returned during deserialization
    // Without this method, deserialization can create a new instance of the Singleton, breaking the pattern.
    private Object readResolve() {
        return INSTANCE;  // Ensure that deserialization returns the same instance
    }

    @Override
    public DBConnectionWithSynchronization clone() {
        return INSTANCE;
    }
}
