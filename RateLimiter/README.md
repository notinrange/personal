# Rate Limiter


```java
RateLimiter limiter = new RateLimiter(100);
if (limiter.allowRequest()) {
    // call the sales channnel
}
```

## Notes

`allowRequest` is synchronized so if lots of threads arrive together, only one
can check/update the count at a time. This means the count cannot go past the
limit for that second.

It resets when the next second starts. This is a fixed second window, so there
could be a burst around the change from one second to the next. I think that is
okay based on the problem saying "current second". If it meant a rolling second
then the approach would need to be different.

## How I tested it

`Main.java` is the Driver Code I used for testing. It has a basic test and a
thread test. For testing it uses a fixed clock so all calls are definitely in
the same second.

I first tested the basic limit with a small value. With a limit of 2, the first
two calls are allowed and the third one is not.

```java
RateLimiter limiter = new RateLimiter(2);

System.out.println(limiter.allowRequest()); // true
System.out.println(limiter.allowRequest()); // true
System.out.println(limiter.allowRequest()); // false
```

I also checked that it starts accepting calls again after the second changes.
For example, if the limit is 1, one call can be allowed in one second and one
more can be allowed in the next second.

For the thread safety part, I created many threads that all called
`allowRequest()` at the same time. I counted how many got `true`. When the
limit was 25, the answer stayed at 25 even when I used hundreds of threads.

Example of the idea:

```java
RAteLimiter limiter = new RateLimiter(25);
AtomicInteger allowed = new AtomicInteger();

// Start lots of threads together. Inside each thread:
if (limiter.allowRequest()) {
    allowed.incrementAndGet();
}

// after all threads finish, allowed.get() should be 25
```

COmpile:
```bash
javac -d out src/main/java/RateLimiter.java src/main/java/Main.java
java -cp out Main
```

The end of the output should say allowed calls: 25 and expected: 25.
