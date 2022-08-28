package com.autobots.ratelimiter.slidingWindow;

import com.autobots.ratelimiter.RateLimiter;

public class SlidingWindowImpl implements RateLimiter {

    BucketCache bucketCache;

    public SlidingWindowImpl(BucketCache bucketCache) {
        this.bucketCache = bucketCache;
    }

    @Override
    public boolean grantAccess(String userId) {
        Long currentTime = System.currentTimeMillis();
        UserSlidingWindowBucket userSlidingWindowBucket = bucketCache.getUserBucket(userId);
        checkAndUpdateQueue(userSlidingWindowBucket, currentTime);
         if(userSlidingWindowBucket.slidingWindow.size() < userSlidingWindowBucket.bucketCapacity) {
            userSlidingWindowBucket.slidingWindow.offer(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue(UserSlidingWindowBucket userSlidingWindowBucket, Long currentTime) {
        if(userSlidingWindowBucket.slidingWindow.isEmpty())
            return;

        long timeElapsed = (currentTime - userSlidingWindowBucket.slidingWindow.peek())/1000;

        while(timeElapsed >= userSlidingWindowBucket.timeWindowInSeconds){
            userSlidingWindowBucket.slidingWindow.poll();
            if(userSlidingWindowBucket.slidingWindow.isEmpty())
                break;
            timeElapsed = (currentTime - userSlidingWindowBucket.slidingWindow.peek())/1000;
        }
    }
}
