package multithreading.executors;

import java.util.*;
import java.util.concurrent.*;

public class CompletableFutureLab {

    public static void main(String[] args) {
        // --- UNCOMMENT ONE METHOD AT A TIME TO LEARN ---

        // LEVEL 1: The Basics (Creation)
        learnBasics();

        // LEVEL 2: Transformation (Data Flow)
        // learnTransformation();

        // LEVEL 3: Chaining (Dependent Tasks)
        // learnChaining();

        // LEVEL 4: Combining (Parallel Tasks)
        // learnCombining();

        // LEVEL 5: Resilience (Timeouts & Handling Errors)
        // learnResilience();

        // LEVEL 6: The "All Of" Pattern (Microservices Aggregation)
        // learnAggregation();
    }

    // ======================================================================
    // LEVEL 1: The Basics (Creation)
    // Concept: "Start a background task without blocking Main Thread"
    // ======================================================================
    public static void learnBasics() {
        System.out.println("--- Level 1: Basics ---");

        // 1. supplyAsync: Use this when you need a RESULT (like a return value)
        CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Database Result";
        });

        // 2. runAsync: Use this when you just want to DO something (void)
        CompletableFuture<Void> fireAndForget = CompletableFuture.runAsync(() -> {
            sleep(500);
            System.out.println("Log saved to disk (Side Effect)");
        });

        // 3. Blocking Get (Try to avoid this in real apps, use .thenAccept instead)
        // .join() is like .get() but throws unchecked exceptions (easier to use)
        String res = futureResult.join();
        System.out.println("Got: " + res);
    }

    // ======================================================================
    // LEVEL 2: Transformation (thenApply)
    // Concept: "Map" - Take X, process it, turn it into Y.
    // Analogy: Flour -> Dough -> Bread
    // ======================================================================
    public static void learnTransformation() {
        System.out.println("--- Level 2: Transformation ---");

        CompletableFuture.supplyAsync(() -> "Hello") // Start with String

                // Step 1: Transform String -> String
                .thenApply(s -> s + " World")

                // Step 2: Transform String -> Integer (Length)
                .thenApply(s -> {
                    System.out.println("Processing: " + s);
                    return s.length();
                })

                // Step 3: Consume final result
                .thenAccept(length -> System.out.println("Final Length: " + length))
                .join();
    }

    // ======================================================================
    // LEVEL 3: Chaining (thenCompose)
    // Concept: "FlatMap" - When Step 1 finishes, use its result to START Step 2.
    // Use Case: Fetch UserID -> Then Fetch UserProfile (Async)
    // ======================================================================
    public static void learnChaining() {
        System.out.println("--- Level 3: Chaining (thenCompose) ---");

        getUserID("alice") // Returns Future<Integer>
                // INCORRECT: .thenApply(id -> getUserProfile(id)) -> Returns Future<Future<String>> (Nested!)

                // CORRECT: "Flatten" the future
                .thenCompose(CompletableFutureLab::getUserProfile) // Returns Future<String>

                .thenAccept(profile -> System.out.println("Final Profile: " + profile))
                .join();
    }

    // Helper methods for Level 3
    private static CompletableFuture<Integer> getUserID(String name) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            System.out.println("Fetched ID for " + name);
            return 123;
        });
    }

    private static CompletableFuture<String> getUserProfile(int id) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            System.out.println("Fetched Profile for ID " + id);
            return "Profile: Admin";
        });
    }

    // ======================================================================
    // LEVEL 4: Combining (thenCombine)
    // Concept: "Zip" - Run A and B in parallel. Wait for BOTH. Combine results.
    // Use Case: Fetch Flight Price AND Fetch Hotel Price -> Calculate Total
    // ======================================================================
    public static void learnCombining() {
        System.out.println("--- Level 4: Combining (thenCombine) ---");
        long start = System.currentTimeMillis();

        CompletableFuture<Integer> flightFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            System.out.println("Flight Found");
            return 500;
        });

        CompletableFuture<Integer> hotelFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1000); // Runs in PARALLEL with flight
            System.out.println("Hotel Found");
            return 300;
        });

        // Wait for BOTH, then sum them
        flightFuture.thenCombine(hotelFuture, Integer::sum)
                .thenAccept(total -> {
                    long time = System.currentTimeMillis() - start;
                    System.out.println("Total Cost: $" + total + " (Took " + time + "ms)");
                    // Notice it takes ~1000ms, not 2000ms!
                }).join();
    }

    // ======================================================================
    // LEVEL 5: Resilience (Timeouts)
    // Concept: "Circuit Breaker" - If it takes too long, give me a default value.
    // ======================================================================
    public static void learnResilience() {
        System.out.println("--- Level 5: Resilience ---");

        CompletableFuture.supplyAsync(() -> {
                    sleep(2000); // Simulate slow DB
                    return "Real DB Result";
                })
                // If not done in 1 second, return "Cached Result" instead
                .completeOnTimeout("Cached Default Value", 1, TimeUnit.SECONDS)

                .thenAccept(result -> System.out.println("Result: " + result))
                .join();
    }

    // ======================================================================
    // LEVEL 6: The "All Of" Pattern
    // Concept: "Scatter-Gather" - Fire 100 requests, wait for all, gather results.
    // ======================================================================
    public static void learnAggregation() {
        System.out.println("--- Level 6: Aggregation (allOf) ---");

        List<CompletableFuture<String>> futures = new ArrayList<>();

        // 1. Fire off 5 tasks
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            futures.add(CompletableFuture.supplyAsync(() -> {
                sleep(id * 200);
                return "Task " + id;
            }));
        }

        // 2. Wrap them in "allOf" (Returns Void)
        CompletableFuture<Void> allDone = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        // 3. When ALL are done, stream the results
        allDone.thenRun(() -> {
            List<String> results = futures.stream()
                    .map(CompletableFuture::join) // Safe to join here because we know they are done
                    .toList();

            System.out.println("All Results Collected: " + results);
        }).join();
    }

    // Utility to sleep without try-catch noise
    private static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) { }
    }
}
