package com.brownfield.pss.book.component;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookingSource {
    String INVENTORY_QUEUE = "InventoryQueue";
    @Output(INVENTORY_QUEUE)
    MessageChannel inventoryQueue();
}
