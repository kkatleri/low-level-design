package com.autobots.lrucache;

public class Node {

    private final String key;
    private String value;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }

    Node next;
    Node prev;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
