package com.brownfield.pss.book.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@EnableBinding(CheckInSink.class)
@Component
public class Receiver {

    private final BookingComponent bookingComponent;

    @Autowired
    public Receiver(BookingComponent bookingComponent) {
        this.bookingComponent = bookingComponent;
    }

    @StreamListener(CheckInSink.CHECK_IN_QUEUE)
    public void processMessage(long bookingId) {
        bookingComponent.updateStatus(BookingStatus.CHECKED_IN, bookingId);
    }

}