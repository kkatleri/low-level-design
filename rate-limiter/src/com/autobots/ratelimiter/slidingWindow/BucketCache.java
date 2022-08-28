package com.autobots.ratelimiter.slidingWindow;


import java.util.HashMap;
import java.util.Map;

public class BucketCache {

    Map<String, UserSlidingWindowBucket> cache = new HashMap<>();

    public void createUserBucket(String userId, int bucketCapacity, long timeWindowInSeconds){
        UserSlidingWindowBucket userSlidingWindowBucket =
                new UserSlidingWindowBucket(bucketCapacity, timeWindowInSeconds);
        cache.put(userId, userSlidingWindowBucket);
    }

    public UserSlidingWindowBucket getUserBucket(String userId){
        return cache.get(userId);
    }
}
