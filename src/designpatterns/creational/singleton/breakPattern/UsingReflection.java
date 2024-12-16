package designpatterns.creational.singleton.breakPattern;

import designpatterns.creational.singleton.DBConnectionWithSynchronization;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UsingReflection {

    public static void main(String[] args) throws NoSuchMethodException {
        DBConnectionWithSynchronization singleton1 = DBConnectionWithSynchronization.getInstance();

        Constructor<DBConnectionWithSynchronization> constructor = DBConnectionWithSynchronization.class.getDeclaredConstructor();

        // Making the Constructor public , breaking the pattern
        constructor.setAccessible(true);
        DBConnectionWithSynchronization singleton2 = null;
        try {
            singleton2 = constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        System.out.println(singleton2 == singleton1); // Different instance
    }
}
