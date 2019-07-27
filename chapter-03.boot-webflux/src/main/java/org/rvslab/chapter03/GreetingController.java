package org.rvslab.chapter03;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
class GreetingController {
    @RequestMapping
    public Mono<Greet> greet() {
        return Mono.just(new Greet("Hello World!"));
    }
}
