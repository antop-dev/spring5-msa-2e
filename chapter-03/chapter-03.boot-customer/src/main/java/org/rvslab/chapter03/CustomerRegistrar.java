package org.rvslab.chapter03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrar {
    private final Logger logger = LoggerFactory.getLogger(CustomerRegistrar.class);

    private final CustomerRepository customerRepository;
    private final Sender sender;

    public CustomerRegistrar(CustomerRepository customerRepository, Sender sender) {
        this.customerRepository = customerRepository;
        this.sender = sender;
    }

    public Customer register(Customer customer) {
        if (customerRepository.findByName(customer.getName()).isPresent()) {
            logger.warn("Duplicate Customer. No Action required");
            return customer;
        }
        customerRepository.save(customer);
        sender.send(customer.getEmail());
        return customer;
    }
}
