package com.brownfield.pss.book;

import com.brownfield.pss.book.entity.Inventory;
import com.brownfield.pss.book.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@EnableDiscoveryClient
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... strings) throws Exception {
        Inventory[] inventories = {
                new Inventory("BF100", "22-JAN-18", 100),
                new Inventory("BF101", "22-JAN-18", 100),
                new Inventory("BF102", "22-JAN-18", 10),
                new Inventory("BF103", "22-JAN-18", 100),
                new Inventory("BF104", "22-JAN-18", 100),
                new Inventory("BF105", "22-JAN-18", 100),
                new Inventory("BF106", "22-JAN-18", 100)};
        Arrays.asList(inventories).forEach(inventory -> inventoryRepository.save(inventory));
    }

}
