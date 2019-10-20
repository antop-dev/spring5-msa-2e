package com.brownfield.pss.search.component;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface SearchSink {
    String INVENTORY_QUEUE = "InventoryQueue";

    @Input(INVENTORY_QUEUE)
    MessageChannel inventoryQueue();
}
