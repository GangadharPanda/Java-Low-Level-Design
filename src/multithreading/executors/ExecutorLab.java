package multithreading.executors;

import java.util.concurrent.*;

public class ExecutorLab {

    public static void main(String[] args) {
        // --- UNCOMMENT ONE METHOD AT A TIME TO TEST ---

        // SCENARIO 1: The Trap (Unbounded Queue)
        //simulateCrash();

        // SCENARIO 2: The Default Fix (AbortPolicy)
        //demonstrateAbortPolicy();

        // SCENARIO 3: The Production Standard (CallerRunsPolicy)
        //demonstrateCallerRunsPolicy();

        // SCENARIO 4: The Silent Killer (DiscardPolicy -> Silent Data Loss)
        //demonstrateDiscardPolicy();

        // SCENARIO 4: The Silent Killer (DiscardPolicy -> Silent Data Loss) + Hanging Future
        //demonstrateDiscardPolicyWithHangingFuture();

        // SCENARIO 5: The Ruthless (DiscardOldestPolicy)
        demonstrateDiscardOldestPolicy();

        // SCENARIO 6: The Thread Explosion (CachedThreadPool -> Native OOM)
        //simulateCachedThreadPoolCrash();
    }

    // ======================================================================
    // SCENARIO 1: The Hidden Danger (newFixedThreadPool)
    // ======================================================================
    public static void simulateCrash() {
        System.out.println("=== Starting Scenario 1: The Unbounded Queue Trap ===");

        // 1. Create the trap: Fixed Pool (Default LinkedBlockingQueue with Integer.MAX_VALUE)
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 2. Submit tasks faster than they can be processed
        int i = 0;
        while (true) { // Infinite loop to guarantee OOM
            final int taskId = i++;
            // Make the task "Heavy" to simulate real request data (e.g., 10KB per request)
            // Without this, you might need millions of tasks to crash a modern JVM.
            final byte[] payload = new byte[10 * 1024];

            executor.submit(() -> {
                try {
                    // Consumer: Very slow processing
                    Thread.sleep(100);
                    // Reference payload to prevent GC optimization
                    if (payload.length > 0) {
                        System.out.println("Task " + taskId + " completed.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            // 3. Monitor the Queue Size and Heap
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) executor;
            if (i % 5000 == 0) {
                long heapUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
                System.out.println("Queue Size: " + tpe.getQueue().size() + " | Heap Used: " + heapUsed + " MB");
            }
        }
    }

    // ======================================================================
    // SCENARIO 2: The "Door" Closes (AbortPolicy)
    // ======================================================================
    public static void demonstrateAbortPolicy() {
        System.out.println("=== Starting Scenario 2: AbortPolicy (Data Loss) ===");

        int coreThreads = 1;
        int maxThreads = 1;
        int queueCapacity = 3; // Small "Waiting Room"

        // 1. Manual Construction with Bounded Queue
        ExecutorService executor = new ThreadPoolExecutor(
                coreThreads,
                maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                new ThreadPoolExecutor.AbortPolicy() // <--- The Default Policy
        );

        // Very IMPORTANT :

        // If Active threads = coreThreads, and
        // If queue is FULL
        // Then Only it will create another thread and create up to :maxThreads

        // 2. Submit 10 tasks.
        // Capacity = 1 (Running) + 3 (Queue) = 4.
        // Tasks 5 through 10 will be rejected.
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            try {
                System.out.println("Submitting Task " + taskId);
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000); // Simulate work
                        System.out.println("   --> Executed Task " + taskId);
                    } catch (InterruptedException e) {
                    }
                });
            } catch (RejectedExecutionException e) {
                // This block handles the "Door Locked" scenario
                System.err.println("!!! Task " + taskId + " was REJECTED! (Data Loss) !!!");
            }
        }

        executor.shutdown();

        // WHAT TO OBSERVE:
        // You will see clear "REJECTED" messages in red (std err).
        // This simulates dropping 500 errors to the user.
    }

    // ======================================================================
    // SCENARIO 3: Backpressure (CallerRunsPolicy) - SENIOR LEVEL PATTERN
    // ======================================================================
    public static void demonstrateCallerRunsPolicy() {
        System.out.println("=== Starting Scenario 3: CallerRunsPolicy (Backpressure) ===");

        int queueCapacity = 2;

        ExecutorService executor = new ThreadPoolExecutor(
                1, // Core
                1, // Max
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy() // <--- The "Do It Yourself" Policy
        );

        int i = 0;
        // Infinite loop to demonstrate sustained load handling
        while (true) {
            final int taskId = i++;
            String threadName = Thread.currentThread().getName();

            // Log before submitting to show the "Pause"
            System.out.println("Producer (" + threadName + ") submitting Task " + taskId);

            executor.submit(() -> {
                try {
                    Thread.sleep(500); // Simulate heavy processing
                    // Check thread name to see WHO is running it (Pool vs Main)
                    String runThread = Thread.currentThread().getName();
                    System.out.println("   --> FINISHED Task " + taskId + " by " + runThread);
                } catch (InterruptedException e) {
                }
            });

            // Small delay to make console readable, but fast enough to overflow queue
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        // WHAT TO OBSERVE:
        // 1. Initially, "Producer (main)" submits fast.

        // 2. Queue fills.
        // 3. Suddenly, you see "FINISHED Task X by main".
        // 4. While "main" is executing, it STOPS submitting new tasks.
        // 5. This is the "Backpressure" that saves the system.
    }

    // ======================================================================
    // SCENARIO 4: Ruthless Efficiency (DiscardOldestPolicy)
    // ======================================================================
    public static void demonstrateDiscardOldestPolicy() {
        System.out.println("=== Starting Scenario 4: DiscardOldestPolicy (Sliding Window) ===");

        // Queue size 2
        ExecutorService executor = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        // Submit 0 to 9 (10 Tasks)
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            System.out.println("Submitting Task " + taskId);
            executor.submit(() -> {
                try {
                    Thread.sleep(200); // Simulate work
                    System.out.println("   --> COMPLETED Task " + taskId);
                } catch (InterruptedException e) {
                }
            });

            // Sleep slightly faster than consumer to ensure queue fills but flows
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // WHAT TO OBSERVE:
        // Task 0 runs.
        // Queue fills with 1, 2.
        // Task 3 comes -> Discards 1 (Oldest). Queue: 2, 3.
        // Task 4 comes -> Discards 2 (Oldest). Queue: 3, 4.
        // Result: You will see gaps in the "COMPLETED" numbers (e.g., 0, 7, 8, 9).
        // The middle tasks were sacrificed.
    }

    // ======================================================================
    // SCENARIO 5: The Silent Killer (DiscardPolicy)
    // ======================================================================
    public static void demonstrateDiscardPolicy() {
        System.out.println("=== Starting Scenario 5: DiscardPolicy (Silent Drops) ===");

        // Queue size 2
        ExecutorService executor = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        // Submit 10 Tasks
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            System.out.println("Submitting Task " + taskId);

            executor.submit(() -> {
                try {
                    Thread.sleep(1000); // Very slow consumer
                    System.out.println("   --> Executed Task " + taskId);
                } catch (InterruptedException e) {
                }
            });

            // Fast producer
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // WHAT TO OBSERVE:
        // You will see "Submitting Task 1... 2... 3... 10".
        // BUT you will only see "Executed Task 1", "Executed Task 2", "Executed Task 3".
        // The other 7 tasks vanish. No Exception. No Error Log. Just gone.
    }


    // ======================================================================
    // SCENARIO 6: The Silent Killer (DiscardPolicy) with Hanging Future
    // ======================================================================
    public static void demonstrateDiscardPolicyWithHangingFuture() {
        // 1. Create a pool that is already full
        ExecutorService executor = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy() // <--- The Trap
        );

        // Fill the pool so the next task gets rejected
        executor.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }); // Core thread busy

        executor.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }); // Queue full

        // 2. Submit the victim task
        // The pool is full, so DiscardPolicy throws this task away.
        // BUT, it still gives you a 'future' object.
        Future<String> future = executor.submit(() -> "I will never run");

        System.out.println("Future obtained. Waiting for result...");

        try {
            // 3. THE TRAP
            // This line will hang FOREVER.
            // The task was discarded, but the future doesn't know that.
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ======================================================================
    // SCENARIO 6: The Thread Explosion (newCachedThreadPool)
    // ======================================================================
    public static void simulateCachedThreadPoolCrash() {
        System.out.println("=== Starting Scenario 6: CachedThreadPool (Thread Explosion) ===");

        // 1. Core = 0, Max = Integer.MAX_VALUE, Queue = SynchronousQueue
        ExecutorService executor = Executors.newCachedThreadPool();

        int i = 0;
        while (true) {
            final int taskId = i++;

            // 2. Submit tasks that hang.
            // Since SynchronousQueue has 0 capacity, and no thread is free (they are sleeping),
            // the pool is FORCED to create a new thread for every single task.
            executor.submit(() -> {
                try {
                    Thread.sleep(100000); // Sleep "forever" to hold the thread
                } catch (InterruptedException e) {
                }
            });

            // 3. Monitor Thread Count
            if (i % 100 == 0) {
                // Approximate count of threads
                int poolSize = ((ThreadPoolExecutor) executor).getPoolSize();
                System.out.println("Tasks Submitted: " + i + " | Active Threads: " + poolSize);
            }
        }

        // WHAT TO OBSERVE:
        // The "Active Threads" will skyrocket: 100, 500, 2000, 5000...
        // Your computer fan will spin up.
        // Eventually: "java.lang.OutOfMemoryError: unable to create new native thread"

        // After 60 seconds , if a thread is idle, it removes that thread
        // If Storm comes and application has to create 1K threads, that's fine
        // But if request keeps growing, that' the issue with the newCachedThreadPool
        //Verdict: If you are building a Server, CachedThreadPool is the enemy. If you are building a Client or a Recursive Algorithm, CachedThreadPool is a friend.
    }


    void shutdownAndAwaitTermination(ExecutorService pool) {
        // Step 1: Disable new tasks from being submitted
        pool.shutdown();
        try {
            // Step 2: Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {

                // Step 3: Cancel currently executing tasks
                pool.shutdownNow();

                // Step 4: Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}