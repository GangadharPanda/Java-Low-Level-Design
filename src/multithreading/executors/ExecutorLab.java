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
        demonstrateDiscardPolicy();

        // SCENARIO 5: The Ruthless (DiscardOldestPolicy)
        // demonstrateDiscardOldestPolicy();
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

        // 2. Submit 10 tasks.
        // Capacity = 1 (Running) + 3 (Queue) = 4.
        // Tasks 5 through 10 will be rejected.
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            final byte[] payload = new byte[1024]; // Simulate data

            try {
                System.out.println("Submitting Task " + taskId);
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000); // Simulate work
                        if (payload.length > 0)
                            System.out.println("   --> Executed Task " + taskId);
                    } catch (InterruptedException e) { }
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
            final byte[] payload = new byte[1024];
            String threadName = Thread.currentThread().getName();

            // Log before submitting to show the "Pause"
            System.out.println("Producer (" + threadName + ") submitting Task " + taskId);

            executor.submit(() -> {
                try {
                    Thread.sleep(500); // Simulate heavy processing
                    // Check thread name to see WHO is running it (Pool vs Main)
                    String runThread = Thread.currentThread().getName();
                    if (payload.length > 0)
                        System.out.println("   --> FINISHED Task " + taskId + " by " + runThread);
                } catch (InterruptedException e) { }
            });

            // Small delay to make console readable, but fast enough to overflow queue
            try { Thread.sleep(50); } catch (InterruptedException e) {}
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
                } catch (InterruptedException e) {}
            });

            // Sleep slightly faster than consumer to ensure queue fills but flows
            try { Thread.sleep(50); } catch (InterruptedException e) {}
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
                } catch (InterruptedException e) { }
            });

            // Fast producer
            try { Thread.sleep(50); } catch (InterruptedException e) {}
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
}