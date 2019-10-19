package com.brownfield.pss.search.controller;

import com.brownfield.pss.search.component.SearchComponent;
import com.brownfield.pss.search.entity.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${origin-airports.shutdown}")
    private String originAirportShutdownList;
    private SearchComponent searchComponent;

    @Autowired
    public SearchRestController(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<Flight> search(@RequestBody SearchQuery query) {
        logger.info("Input : {}", query);
        if (Arrays.asList(originAirportShutdownList.split(","))
                .contains(query.getOrigin())) {
            logger.info("The origin airport is in shutdown state");
            return new ArrayList<>();
        }
        return searchComponent.search(query);
    }

}
