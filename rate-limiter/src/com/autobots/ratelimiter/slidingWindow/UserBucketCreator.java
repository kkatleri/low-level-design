package com.autobots.ratelimiter.slidingWindow;

public class UserBucketCreator {

    BucketCache cache;

    public UserBucketCreator(BucketCache cache) {
        this.cache = cache;
    }

    public void createUserBucket(String userId, int bucketCapacity, long timeWindowInSeconds){
        cache.createUserBucket(userId,bucketCapacity,timeWindowInSeconds);
    }
}
