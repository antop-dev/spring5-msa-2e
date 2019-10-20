package com.brownfield;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SearchApiGatewayComponent {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultHub")
    public String getHub() {
        return restTemplate.getForObject("http://search-service/search/hub", String.class);
    }

    public String getDefaultHub() {
        return "Possibly SFO";
    }

}
