package com.brownfield.pss.search.controller;

import com.brownfield.pss.search.component.SearchComponent;
import com.brownfield.pss.search.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchRestController {

    private SearchComponent searchComponent;

    @Autowired
    public SearchRestController(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<Flight> search(@RequestBody SearchQuery query) {
        return searchComponent.search(query);
    }

}
