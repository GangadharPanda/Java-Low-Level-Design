package designpatterns.creational.singleton.breakPattern;

import designpatterns.creational.singleton.DBConnectionWithSynchronization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UsingSerialization {

    public static void main(String[] args) throws Exception {
        DBConnectionWithSynchronization singleton1 = DBConnectionWithSynchronization.getInstance();

        // Serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        out.writeObject(singleton1);
        out.close();

        // Deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
        DBConnectionWithSynchronization singleton2 = (DBConnectionWithSynchronization) in.readObject();
        in.close();

        System.out.println(singleton1 == singleton2); // False, if DBConnectionWithSynchronization class does not have private Object readResolve(){ return INSTANCE}
    }
}
