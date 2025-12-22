package collections.map;

import java.util.*;

// 1. The Node Class
class SimpleEntry<K, V> {
    K key;
    V value;
    SimpleEntry<K, V> next;

    public SimpleEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

// 2. The Custom Map Implementation
class MyHashMap<K, V> {
    private static final int SIZE = 16;
    private final SimpleEntry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new SimpleEntry[SIZE];
    }

    // Helper: Determine index (Handle null -> 0)
    private int getBucketIndex(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        SimpleEntry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new SimpleEntry<>(key, value);
        } else {
            while (true) {
                // Check: null-safe equality
                // 1. (entry.key == key) handles null==null
                // 2. (key != null && equals) handles normal objects
                if (Objects.equals(key, entry.key)) {
                    entry.value = value; // Update
                    return;
                }
                if (entry.next == null) {
                    entry.next = new SimpleEntry<>(key, value); // Append
                    return;
                }
                entry = entry.next;
            }
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        SimpleEntry<K, V> entry = table[index];

        while (entry != null) {
            // Null-safe equality check
            if (Objects.equals(key, entry.key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    // Debug method to visualize buckets
    public void printDebug() {
        for (int i = 0; i < SIZE; i++) {
            if (table[i] != null) {
                System.out.print("Bucket " + i + ": ");
                SimpleEntry<K,V> e = table[i];
                while(e != null) {
                    System.out.print("[" + e.key + "=" + e.value + "] -> ");
                    e = e.next;
                }
                System.out.println("null");
            }
        }
    }
}

// 3. Test Runner
public class MyHashMapLab {
    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();

        // Test 1: Simple Put/Get
        System.out.println("--- Test 1: Basic ---");
        map.put("USA", "Washington DC");
        map.put("India", "New Delhi");
        map.put("UK", "London");

        System.out.println("India Capital: " + map.get("India"));

        // Test 2: Updates
        System.out.println("\n--- Test 2: Update ---");
        map.put("USA", "New York"); // Wrong capital
        System.out.println("USA (After update): " + map.get("USA")); // Should be New York
        map.put("USA", "Washington DC"); // Fix it

        // Test 3: Null Keys
        System.out.println("\n--- Test 3: Null Keys ---");
        map.put(null, "The Void");
        System.out.println("Value for null key: " + map.get(null));

        // Null should always be in Bucket 0
        map.put(null, "Updated Void");
        System.out.println("Value for null key (Update): " + map.get(null));

        System.out.println("\n--- Structure ---");
        map.printDebug();
    }
}
