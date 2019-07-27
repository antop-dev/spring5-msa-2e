package org.rvslab.chapter03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static org.rvslab.chapter03.BootMessagingApplication.QUEUE_NAME;

@Component
class Receiver {
    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = QUEUE_NAME)
    public void processMessage(String message) {
        logger.info("payload = {}", message);
    }
}
