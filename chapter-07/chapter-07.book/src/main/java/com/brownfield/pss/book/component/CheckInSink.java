package com.brownfield.pss.book.component;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CheckInSink {
    String CHECK_IN_QUEUE = "CheckInQueue";

    @Input(CHECK_IN_QUEUE)
    MessageChannel checkInQueue();
}
