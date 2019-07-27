package org.rvslab.chapter03;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class BootCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootCustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerRepository repo) {
        Faker faker = new Faker();
        return (evt) -> IntStream.range(0, 100).forEach(i -> repo.save(
                Customer.of(faker.name().fullName(), faker.name().username().toLowerCase() + "@boot.com")
        ));
    }

}
