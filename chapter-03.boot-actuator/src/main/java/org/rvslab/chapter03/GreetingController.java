package org.rvslab.chapter03;

import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {
    private final TPSHealth health;

    public GreetingController(TPSHealth health) {
        this.health = health;
    }

    @GetMapping("/")
    public HttpEntity<Greet> greet() {
        health.updateTx();
        if (health.health().getStatus() == Status.OUT_OF_SERVICE) {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new Greet((String) health.health().getDetails().get("message")));
        }
        return ResponseEntity.ok(new Greet("Hello World!"));
    }

}
