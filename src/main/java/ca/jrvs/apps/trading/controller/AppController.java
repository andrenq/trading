package ca.jrvs.apps.trading.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class AppController {

    @GetMapping(path = "/")
    public String appController() {
        return "I'm alive!";
    }

}
