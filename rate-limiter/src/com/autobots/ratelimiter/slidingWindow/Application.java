package com.autobots.ratelimiter.slidingWindow;

import com.autobots.ratelimiter.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    static RateLimiter rateLimiter;

    public static void main(String[] args) {

        BucketCache bucketCache = new BucketCache();

        UserBucketCreator userBucketCreator = new UserBucketCreator(bucketCache);

        //Add 10 new tokens every minute
        userBucketCreator.createUserBucket("User-1", 10, 3);
        userBucketCreator.createUserBucket("User-2", 5,2);

        rateLimiter = new SlidingWindowImpl(bucketCache);

        //Single threaded testing
        /*for(int i=0; i<50; i++) {
            accessApplication("User-1");
        }*/

        //Multi-threaded testing
        /**/ExecutorService executorService = Executors.newFixedThreadPool(50);
        for(int i=0; i<15; i++) {
            executorService.execute(() -> {
                accessApplication("User-1");
                //accessApplication("User-2");
            });
        }

        //Simulates 3 mins of time to for sliding window logic to work for User-1
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n****************************************\n");

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
