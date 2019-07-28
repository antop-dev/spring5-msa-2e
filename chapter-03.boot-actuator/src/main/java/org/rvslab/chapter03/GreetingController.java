package org.rvslab.chapter03;

import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.buffer.BufferCounterService;
import org.springframework.boot.actuate.metrics.buffer.BufferGaugeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {
    private final TPSHealth health;
    private final CounterService counterService;
    private final GaugeService gaugeService;

    public GreetingController(TPSHealth health, BufferCounterService counterService, BufferGaugeService gaugeService) {
        this.health = health;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @GetMapping("/")
    public HttpEntity<Greet> greet() {
        health.updateTx();
        if (health.health().getStatus() == Status.OUT_OF_SERVICE) {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new Greet((String) health.health().getDetails().get("message")));
        }

        counterService.increment("greet");
        gaugeService.submit("greet", 1.0);
        return ResponseEntity.ok(new Greet("Hello World!"));
    }

}
