import java.time.Clock;
import java.util.Objects;

public final class RateLimiter {
    private final int maxRequestsPerSecond;
    private final Clock clock;

    // Keep track of which second the counter belongs to.
    private long currentSecond = Long.MIN_VALUE;
    private int requestsInCurrentSecond;

    public RateLimiter(int maxRequestsPerSecond) {
        this(maxRequestsPerSecond, Clock.systemUTC());
    }

    public RateLimiter(int maxRequestsPerSecond, Clock clock) {
        if (maxRequestsPerSecond <= 0) {
            throw new IllegalArgumentException("Limit needs to be bigger than 0");
        }
        this.maxRequestsPerSecond = maxRequestsPerSecond;
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    // This needs to be one step, otherwise two threads could both get allowed.
    public synchronized boolean allowRequest() {
        long second = clock.millis() / 1_000;

        // A new second means this counter is no longer useful.
        if (second != currentSecond) {
            currentSecond = second;
            requestsInCurrentSecond = 0;
        }

        // Do not increase it if this second is already full.
        if (requestsInCurrentSecond >= maxRequestsPerSecond) {
            return false;
        }

        // Reserve one of the available calls before returning true.
        requestsInCurrentSecond++;
        return true;
    }
}
