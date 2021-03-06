package com.brownfield.pss.fares;

import com.brownfield.pss.fares.entity.Fare;
import com.brownfield.pss.fares.repository.FaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EnableDiscoveryClient
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    FaresRepository faresRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {
        Fare[] fares = {
                new Fare("BF100", "22-JAN-18", "101"),
                new Fare("BF101", "22-JAN-18", "101"),
                new Fare("BF102", "22-JAN-18", "102"),
                new Fare("BF103", "22-JAN-18", "103"),
                new Fare("BF104", "22-JAN-18", "104"),
                new Fare("BF105", "22-JAN-18", "105"),
                new Fare("BF106", "22-JAN-18", "106")};
        List<Fare> list = Arrays.stream(fares).collect(Collectors.toList());
        list.forEach(fare -> faresRepository.save(fare));
    }

}
