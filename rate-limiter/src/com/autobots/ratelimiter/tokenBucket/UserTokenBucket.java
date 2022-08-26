package com.autobots.ratelimiter.tokenBucket;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UserTokenBucket {

    AtomicInteger bucketCapacity;
    int refreshRate;
    AtomicInteger currentCapacity;
    AtomicLong currentTimestamp;
    AtomicLong lastUpdatedTime;


    public UserTokenBucket(AtomicInteger bucketCapacity, int refreshRate, AtomicInteger currentCapacity, AtomicLong currentTimestamp, AtomicLong lastUpdatedTime) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.currentCapacity = currentCapacity;
        this.currentTimestamp = currentTimestamp;
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
