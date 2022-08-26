package com.autobots.ratelimiter.tokenBucket;

import com.autobots.ratelimiter.RateLimiter;

public class TokenBucketImpl implements RateLimiter {

    BucketCache cache;

    public TokenBucketImpl(BucketCache cache) {
        this.cache = cache;
    }

    @Override
    public boolean grantAccess(String userId) {
        UserTokenBucket userTokenBucket = cache.getUserBucket(userId);

        refreshUserTokenBucket(userTokenBucket);

        if(userTokenBucket.currentCapacity.get() > 0){
            userTokenBucket.currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refreshUserTokenBucket(UserTokenBucket userTokenBucket) {

        long currentTime = System.currentTimeMillis();
        //Add "refreshRate" amount of new tokens every minute(1000*60)
        int additionalToken =
                 (int) ((currentTime - userTokenBucket.lastUpdatedTime.get())/(1000*60)) * userTokenBucket.refreshRate;
        userTokenBucket.currentCapacity.set(
               Math.min(userTokenBucket.currentCapacity.get()+additionalToken,
                       userTokenBucket.bucketCapacity.get()));
        userTokenBucket.lastUpdatedTime.set(currentTime);
    }
}
