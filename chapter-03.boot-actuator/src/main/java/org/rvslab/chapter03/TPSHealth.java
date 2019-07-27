package org.rvslab.chapter03;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TPSHealth implements HealthIndicator {
    private TPSCounter counter;

    public TPSHealth() {
        counter = new TPSCounter();
    }

    @Override
    public Health health() {
        boolean health = counter.isWeak();
        if (health) {
            return Health.outOfService().withDetail("message", "Too many requests").build();
        }
        return Health.up().build();
    }

    public void updateTx() {
        if (counter.isExpired()) {
            counter = new TPSCounter();
        }
        counter.increment();
    }

}
