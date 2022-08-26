package com.autobots.ratelimiter.tokenBucket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BucketCache {

    Map<String, UserTokenBucket> cache = new HashMap<>();

    public void createUserBucket(String userId, int bucketCapacity, int refreshRate){
        UserTokenBucket userTokenBucket =
                new UserTokenBucket(
                        new AtomicInteger(bucketCapacity),
                        refreshRate,
                        new AtomicInteger(bucketCapacity),
                        new AtomicLong(System.currentTimeMillis()),
                        new AtomicLong(System.currentTimeMillis()));
        cache.put(userId, userTokenBucket);
    }

    public UserTokenBucket getUserBucket(String userId){
        return cache.get(userId);
    }
}
