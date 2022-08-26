package com.autobots.ratelimiter.leakybucket;

public class UserBucketCreator {

    BucketCache cache;

    public UserBucketCreator(BucketCache cache) {
        this.cache = cache;
    }

    public void createUserBucket(String userId, int capacity){
        cache.createUserBucket(userId,capacity);
    }
}
