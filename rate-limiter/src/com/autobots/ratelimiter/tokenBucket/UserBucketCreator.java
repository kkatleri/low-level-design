package com.autobots.ratelimiter.tokenBucket;

import com.autobots.ratelimiter.tokenBucket.BucketCache;

public class UserBucketCreator {

    BucketCache cache;

    public UserBucketCreator(BucketCache cache) {
        this.cache = cache;
    }

    public void createUserBucket(String userId, int bucketCapacity, int refreshRate){
        cache.createUserBucket(userId,bucketCapacity,refreshRate);
    }
}
