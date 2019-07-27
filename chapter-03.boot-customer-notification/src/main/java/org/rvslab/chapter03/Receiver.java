package org.rvslab.chapter03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private final Logger logger = LoggerFactory.getLogger(Receiver.class);
    public static final String QUEUE_NAME = "CustomerQ";

    private final Mailer mailer;

    public Receiver(Mailer mailer) {
        this.mailer = mailer;
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void processMessage(String email) {
        logger.info("send mail to " + email);
        mailer.sendMail(email);
    }

}
