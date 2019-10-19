package org.rvslab.chapter03;

import java.util.Calendar;
import java.util.concurrent.atomic.LongAdder;

class TPSCounter {
    private final LongAdder count;
    private final int threshold = 5;
    private final Calendar expiry;

    public TPSCounter() {
        count = new LongAdder();
        expiry = Calendar.getInstance();
        expiry.add(Calendar.MINUTE, 1);
    }

    public boolean isExpired() {
        return Calendar.getInstance().after(expiry);
    }

    public boolean isWeak() {
        return count.intValue() > threshold;
    }

    public void increment() {
        count.increment();
    }

}
