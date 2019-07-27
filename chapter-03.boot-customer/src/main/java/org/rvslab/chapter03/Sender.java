package org.rvslab.chapter03;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Sender {
    public static final String QUEUE_NAME = "CustomerQ";

    private final RabbitMessagingTemplate template;

    public Sender(RabbitMessagingTemplate template) {
        this.template = template;
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    public void send(String message) {
        template.convertAndSend(QUEUE_NAME, message);
    }
}
