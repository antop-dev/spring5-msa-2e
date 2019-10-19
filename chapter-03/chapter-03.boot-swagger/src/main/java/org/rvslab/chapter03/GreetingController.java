package org.rvslab.chapter03;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {
    @ApiOperation(value = "헬로우 월드!", httpMethod = "GET")
    @GetMapping("/")
    public Greet greet(@RequestParam(name = "name", required = false, defaultValue = "World!") String name) {
        return new Greet("Hello " + name + "!");
    }
}
