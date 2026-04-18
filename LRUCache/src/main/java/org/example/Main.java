package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        System.out.println("== LRU Cache Demo (capcity=3) ====");

        LRUCache<Integer,String> cache = new LRUCache<>(3);

        // Fill cache
        System.out.println("put(1, \"one\")");  cache.put(1, "one");
        System.out.println("put(2, \"two\")");  cache.put(2, "two");
        System.out.println("put(3, \"three\")"); cache.put(3, "three");
        System.out.println("Size: " + cache.size() + "\n");
        // DLL: [3] ↔ [2] ↔ [1]   (3 is MRU)

        // Get key 1 → moves it to MRU
        System.out.println("get(1) → " + cache.get(1));
        System.out.println("  (key 1 moved to MRU)");
        // DLL: [1] ↔ [3] ↔ [2]   (2 is now LRU)

        // Inserting key 4 → evicts LRU (key 2)
        System.out.println("\nput(4, \"four\") → should evict key 2:");
        cache.put(4, "four");
        // DLL: [4] ↔ [1] ↔ [3]

        System.out.println("\nget(2) → " + cache.get(2) + "  (null = evicted correctly)");
        System.out.println("get(1) → " + cache.get(1));
        System.out.println("get(3) → " + cache.get(3));
        System.out.println("get(4) → " + cache.get(4));

        // Update existing key
        System.out.println("\nput(1, \"ONE\") → update existing");
        cache.put(1, "ONE");
        System.out.println("get(1) → " + cache.get(1) + "  (updated value)");

        // Fill to trigger another eviction
        System.out.println("\nput(5, \"five\") → should evict LRU:");
        cache.put(5, "five");
        System.out.println("Size: " + cache.size());


        System.out.println("\n=== Thread-safety demo ===");
        LRUCache<String, Integer> threadCache = new LRUCache<>(50);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) threadCache.put("k" + i, i);
        }, "writer");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) threadCache.get("k" + i);
        }, "reader");
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        System.out.println("Concurrent ops done. Cache size: " + threadCache.size() + " (max 50)");


    }
}
