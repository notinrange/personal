import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Using one fixed second here makes the result easy to check.
        Clock testClock = Clock.fixed(Instant.ofEpochSecond(100), ZoneOffset.UTC);
        System.out.println("Test clock: " + testClock.instant());
        RateLimiter basicLimiter = new RateLimiter(2, testClock);
        System.out.println("Basic test:");
        System.out.println(basicLimiter.allowRequest()); // true
        System.out.println(basicLimiter.allowRequest()); // true
        System.out.println(basicLimiter.allowRequest()); // false

        int limit = 25;
        int numberOfThreads = 100;
        RateLimiter threadLimiter = new RateLimiter(limit, testClock);
        AtomicInteger allowed = new AtomicInteger();
        CountDownLatch ready = new CountDownLatch(numberOfThreads);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch finished = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                ready.countDown();
                try {
                    start.await(); // wait so the threads call it at almost the same time
                    if (threadLimiter.allowRequest()) {
                        allowed.incrementAndGet();
                    }
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                } finally {
                    finished.countDown();
                }
            });
            thread.start();
        }

        ready.await();
        start.countDown();
        finished.await();

        System.out.println("\nThread test:");
        System.out.println("Allowed calls: " + allowed.get());
        System.out.println("Expected: " + limit);
    }
}
