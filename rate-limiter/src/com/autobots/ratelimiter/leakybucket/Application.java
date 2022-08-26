package com.autobots.ratelimiter.leakybucket;

import com.autobots.ratelimiter.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    static RateLimiter rateLimiter;

    public static void main(String[] args) {

        BucketCache bucketCache = new BucketCache();

        UserBucketCreator userBucketCreator = new UserBucketCreator(bucketCache);

        userBucketCreator.createUserBucket("User-1", 10);
        userBucketCreator.createUserBucket("User-2", 5);

        rateLimiter = new LeakyBucketImpl(bucketCache);

        //Single threaded testing
        /*for(int i=0; i<12; i++) {
            accessApplication("User-1");
        }*/

        //Multi-threaded testing
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for(int i=0; i<15; i++) {
            executorService.execute(() -> {
                accessApplication("User-1");
                //accessApplication("User-2");
            });
        }
        executorService.shutdown();
    }

    public static void accessApplication(String userId){
        if(rateLimiter.grantAccess(userId))
            System.out.println(Thread.currentThread().getName() + "::" + userId + " Access granted!!");
        else
            System.out.println(Thread.currentThread().getName() + "::" + userId + " Access Denied - Too many requests :(");

    }
}
