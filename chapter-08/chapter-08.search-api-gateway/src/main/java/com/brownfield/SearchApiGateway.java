package com.brownfield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCircuitBreaker
@RestController
public class SearchApiGateway {
    private static final Logger logger = LoggerFactory.getLogger(SearchApiGateway.class);

    @Autowired
    private SearchApiGatewayComponent component;

    @RequestMapping("/hubongw")
    public String getHub() {
        logger.info("Search Request in API gateway for getting Hub, forwarding to search-service");
        return component.getHub();
    }

}
