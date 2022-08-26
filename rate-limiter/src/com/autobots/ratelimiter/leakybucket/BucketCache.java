package com.autobots.ratelimiter.leakybucket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BucketCache {

    Map<String, BlockingQueue<Integer>> cache = new HashMap<>();

    public void createUserBucket(String userId, int capacity){
        cache.put(userId, new LinkedBlockingQueue<>(capacity));
    }

    public BlockingQueue getUserBucket(String userId){
        return cache.get(userId);
    }
}
