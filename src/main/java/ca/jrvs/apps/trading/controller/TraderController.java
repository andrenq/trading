package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;


@RestController
@RequestMapping("/trader")
public class TraderController {

    @Autowired
    RegisterService registerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "save")
    public Trader createTrader() {
        Trader trader = new Trader("Andre", "Queiroz", Date.valueOf("1983-04-29"), "Canada", "andre@gmail.com");
        Trader newTrader = registerService.createTrader(trader);
        registerService.deleteTrader(newTrader);
        return newTrader;
    }
}
