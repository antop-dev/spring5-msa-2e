package org.rvslab.chapter03;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootMessagingApplication implements CommandLineRunner {
    public static final String QUEUE_NAME = "TestQ";

    private final Sender sender;

    public BootMessagingApplication(Sender sender) {
        this.sender = sender;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootMessagingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // CommandLineRunner.run() 은 실패시 스프링이 올라가지 않는다?
        sender.send("Hello Messaging..!!!");
    }

}