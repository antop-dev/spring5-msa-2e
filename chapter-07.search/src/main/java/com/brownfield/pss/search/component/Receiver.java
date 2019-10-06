package com.brownfield.pss.search.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@EnableBinding(SearchSink.class)
@Component
public class Receiver {
    private SearchComponent searchComponent;

    @Autowired
    public Receiver(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @StreamListener(SearchSink.INVENTORY_QUEUE)
    public void processMessage(Map<String, Object> fare) {
        searchComponent.updateInventory((String) fare.get("FLIGHT_NUMBER"), (String) fare.get("FLIGHT_DATE"), (int) fare.get("NEW_INVENTORY"));
        //call repository and update the fare for the given flight
    }

}