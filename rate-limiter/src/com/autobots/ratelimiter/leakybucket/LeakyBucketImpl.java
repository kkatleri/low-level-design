package com.autobots.ratelimiter.leakybucket;

import com.autobots.ratelimiter.RateLimiter;

import java.util.concurrent.BlockingQueue;

public class LeakyBucketImpl implements RateLimiter {

    BucketCache cache;

    public LeakyBucketImpl(BucketCache cache) {
        this.cache = cache;
    }

    @Override
    public boolean grantAccess(String userId) {

        /* Simulating race condition in distributed environment
           Two or more requests can come concurrently at exactly same time and access cache bucket
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        BlockingQueue bucket = cache.getUserBucket(userId);
        if(bucket.remainingCapacity()>0){
            bucket.add(1);
            return true;
        }
        return false;
    }
}
