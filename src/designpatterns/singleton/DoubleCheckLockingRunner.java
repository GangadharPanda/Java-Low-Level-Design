package designpatterns.singleton;

public class DoubleCheckLockingRunner {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(DBConnectionWithDoubleCheckLocking.getInstance());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(DBConnectionWithDoubleCheckLocking.getInstance());
            }
        });

        t1.start();
        t2.start();
    }

}
