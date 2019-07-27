package org.rvslab.chapter03;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerRegistrar customerRegistrar;

    public CustomerController(CustomerRegistrar customerRegistrar) {
        this.customerRegistrar = customerRegistrar;
    }

    @PostMapping(path = "/register") // value is alias for path
    public Customer register(@RequestBody Customer customer) {
        return customerRegistrar.register(customer);
    }
}
