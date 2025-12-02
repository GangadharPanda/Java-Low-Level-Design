package multithreading.executors;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public NamedThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        // 1. Create the thread
        Thread t = new Thread(r);

        // 2. Set the custom name
        t.setName(namePrefix + "-" + threadNumber.getAndIncrement());

        // 3. (Optional) Set as Daemon or Priority
        // t.setDaemon(false);
        // t.setPriority(Thread.NORM_PRIORITY);

        // 4. (Critical) Set UncaughtExceptionHandler
        // This catches exceptions even from execute() calls!
        t.setUncaughtExceptionHandler((thread, ex) -> {
            System.err.println("Thread " + thread.getName() + " threw exception: " + ex);
        });

        return t;
    }
}
