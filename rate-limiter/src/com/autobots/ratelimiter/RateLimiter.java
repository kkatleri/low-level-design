package com.autobots.ratelimiter;

public interface RateLimiter {

    boolean grantAccess(String userId);
}
