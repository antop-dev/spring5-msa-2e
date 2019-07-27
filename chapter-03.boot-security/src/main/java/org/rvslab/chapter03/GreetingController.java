package org.rvslab.chapter03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {
    @GetMapping("/")
    public Greet greet() {
        return new Greet("Hello World!");
    }
}
