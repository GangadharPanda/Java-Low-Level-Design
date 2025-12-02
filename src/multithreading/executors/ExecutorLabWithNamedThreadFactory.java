package multithreading.executors;

import java.util.concurrent.*;

public class ExecutorLabWithNamedThreadFactory {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //demonstrateThreadPoolName();
        howDoesNamedThreadFactoryThrowsException();
    }

    public static void demonstrateThreadPoolName() {
        System.out.println("=== Starting Scenario 2: AbortPolicy (Data Loss) ===");

        int coreThreads = 2;
        int maxThreads = 2;
        int queueCapacity = 3; // Small "Waiting Room"

        // 1. Manual Construction with Bounded Queue
        ExecutorService executor = new ThreadPoolExecutor(
                coreThreads,
                maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                new NamedThreadFactory("ABORT-POLICY"),
                new ThreadPoolExecutor.AbortPolicy() // <--- The Default Policy
        );

        for (int i = 1; i <= 100; i++) {
            final int taskId = i;
            final byte[] payload = new byte[1024]; // Simulate data

            try {
                System.out.println("Submitting Task " + taskId);
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000); // Simulate work
                        if (payload.length > 0)
                            System.out.println(Thread.currentThread().getName() + "   --> Executed Task " + taskId);
                    } catch (InterruptedException e) {
                    }
                });
            } catch (RejectedExecutionException e) {
                System.err.println("!!! Task " + taskId + " was REJECTED! (Data Loss) !!!");
            }
        }

        executor.shutdown();

        // WHAT TO OBSERVE:
        // Thread Pool name will be ABORT-POLICY-<number>
    }

    static void howDoesNamedThreadFactoryThrowsException() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1, new NamedThreadFactory("Worker"));

        // Case A: execute() -> CRASHES
        // The UncaughtExceptionHandler WILL fire.
        // You see: "Thread Worker-1 threw exception: java.lang.RuntimeException: Boom"
        pool.execute(() -> {
            throw new RuntimeException("Boom");
        });

        // Case B: submit() -> SILENT
        // The UncaughtExceptionHandler will NOT fire.
        // The exception is trapped inside the Future.
        // You see: NOTHING.
        Future<Void> ans = pool.submit(() -> {
            throw new RuntimeException("Silent Boom");
        });

        // when we try to get the value from the future object , we will see the exception
        ans.get();
    }
}