package designpatterns.creational.singleton.breakPattern;

import designpatterns.creational.singleton.DBConnectionWithSynchronization;

public class UsingClone {

    public static void main(String[] args) {
        DBConnectionWithSynchronization singleton1 = DBConnectionWithSynchronization.getInstance();

        DBConnectionWithSynchronization singleton2 = (DBConnectionWithSynchronization) singleton1.clone();

        System.out.println(singleton1 == singleton2);// False, If the clone method inside DBConnectionWithSynchronization does not return the same INSTANCE
    }
}
