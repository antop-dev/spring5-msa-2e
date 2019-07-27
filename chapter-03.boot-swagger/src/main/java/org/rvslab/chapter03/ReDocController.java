package org.rvslab.chapter03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class ReDocController {

    @ApiIgnore
    @GetMapping("/redoc.html")
    public void redoc() {
    }

}
