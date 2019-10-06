package com.brownfield.pss.checkin.component;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CheckInSource {
    String CHECK_IN_QUEUE = "CheckInQueue";
    @Output(CHECK_IN_QUEUE)
    MessageChannel checkInQueue();
}
