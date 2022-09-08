package com.autobots.lrucache;

public class Application {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);

        cache.put("1", "a");
        cache.put("2", "b");
        cache.put("3", "c");

        cache.peek();
        cache.printHeadOfQueue();
        cache.printTailOfQueue();

        System.out.println(cache.get("2"));

        cache.peek();
        cache.printHeadOfQueue();
        cache.printTailOfQueue();

        cache.put("4","d");

        cache.peek();
        cache.printHeadOfQueue();
        cache.printTailOfQueue();

        System.out.println(cache.get("3"));

        cache.peek();
        cache.printHeadOfQueue();
        cache.printTailOfQueue();
    }
}
