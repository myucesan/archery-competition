package nl.hva.ict.se.sands;

import java.util.concurrent.TimeUnit;

public class Stopwatch {
    private final long startTime;
    public Stopwatch() {
        startTime = System.nanoTime();
    }

    long elapsedTime() {
        long elapsedTime = System.nanoTime() - startTime;
        long durationInMs = TimeUnit.NANOSECONDS.toMillis(elapsedTime);
        return durationInMs;
    }
}
