package org.rvslab.chapter03;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static org.rvslab.chapter03.BootMessagingApplication.QUEUE_NAME;

@Component
class Sender {
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
