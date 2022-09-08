package com.autobots.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Map<String, Node> cache;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(this.capacity);
    }

    Node head; //Represents most recently used item in the cache
    Node tail; //Represents least recently used item in the cache

    public String get(String key){
        if(!cache.containsKey(key))
            return null;

        Node node = cache.get(key);
        if(node!=this.head)
            moveNodeToHead(node);

        return node.getValue();
    }

    private void moveNodeToHead(Node node) {

        Node clone = new Node(node.getKey(), node.getValue());
        deleteNodeFromQueue(node);
        addNodeToHeadOfQueue(clone);
    }

    private void deleteNodeFromQueue(Node node) {
        if(node == this.tail) {
            popNodeFromTailOfQueue();
            return;
        }

        Node temp = node.prev;
        node = node.next;
        node.prev = temp;
        temp.next = node;
    }

    public void put(String key, String value){

        if(cache.containsKey(key))
            cache.put(key, new Node(key, value));

        if(size >= capacity){
            Node tailNode = popNodeFromTailOfQueue();
            cache.remove(tailNode.getKey());
        }

        Node node = new Node(key, value);
        cache.put(key, node);
        this.size++;

        addNodeToHeadOfQueue(node);
    }

    private Node popNodeFromTailOfQueue() {
        Node node = this.tail;
        this.tail = this.tail.prev;
        this.tail.next = null;
        return node;
    }

    private void addNodeToHeadOfQueue(Node node) {
        if(this.head==null){
            this.head = node;
            this.tail = node;
            return;
        }

        node.next = this.head;
        this.head.prev = node;
        this.head = node;
    }

    public void peek(){
        System.out.println("Cache content - " + cache);
    }

    public void printHeadOfQueue(){
        System.out.println("Head of the Queue - " + this.head);
    }

    public void printTailOfQueue(){
        System.out.println("Tail of the Queue - " + this.tail);
    }
}
