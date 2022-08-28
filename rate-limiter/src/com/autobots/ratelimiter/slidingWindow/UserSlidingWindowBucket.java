package com.autobots.ratelimiter.slidingWindow;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class UserSlidingWindowBucket {

    Queue<Long> slidingWindow;
    int bucketCapacity;
    long timeWindowInSeconds;

    public UserSlidingWindowBucket(int bucketCapacity, long timeWindowInSeconds) {
        this.bucketCapacity = bucketCapacity;
        this.slidingWindow = new ConcurrentLinkedDeque<>();
        this.timeWindowInSeconds = timeWindowInSeconds;
    }
}
