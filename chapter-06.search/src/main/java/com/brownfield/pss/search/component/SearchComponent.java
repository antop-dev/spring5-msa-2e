package com.brownfield.pss.search.component;

import com.brownfield.pss.search.controller.SearchQuery;
import com.brownfield.pss.search.entity.Flight;
import com.brownfield.pss.search.entity.Inventory;
import com.brownfield.pss.search.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SearchComponent {
    private static final Logger logger = LoggerFactory.getLogger(SearchComponent.class);
    private FlightRepository flightRepository;


    @Autowired
    public SearchComponent(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> search(SearchQuery query) {
        List<Flight> flights;
        if (query.isEmpty()) {
            flights = flightRepository.findAll();
        } else {
            flights = flightRepository.findByOriginAndDestinationAndFlightDate(
                    query.getOrigin(),
                    query.getDestination(),
                    query.getFlightDate());
        }
        List<Flight> searchResult = new ArrayList<>();
        searchResult.addAll(flights);
        flights.forEach(flight -> {
            int inv = flight.getInventory().getCount();
            if (inv < 0) {
                searchResult.remove(flight);
            }
        });
        return searchResult;
    }

    public void updateInventory(String flightNumber, String flightDate, int inventory) {
        logger.info("Updating inventory for flight " + flightNumber + " innventory " + inventory);
        Flight flight = flightRepository.findByFlightNumberAndFlightDate(flightNumber, flightDate);
        Inventory inv = flight.getInventory();
        inv.setCount(inventory);
        flightRepository.save(flight);
    }
}
