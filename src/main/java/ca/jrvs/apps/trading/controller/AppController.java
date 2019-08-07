package ca.jrvs.apps.trading.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/health")
public class AppController {

    @GetMapping
    public String appController() throws UnknownHostException {
        String host = (InetAddress.getLocalHost().getHostName());
        return "I'm alive! \n" + host;
    }

}
