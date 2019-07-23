package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.service.FundTransferService;
import ca.jrvs.apps.trading.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
@RequestMapping("/trader")
public class TraderController {

    @Autowired
    RegisterService registerService;
    @Autowired
    FundTransferService fundTransferService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/save")
    public Trader createTrader() {
        Trader trader = new Trader("Andre", "Queiroz", Date.valueOf("1983-04-29"), "Canada", "andre@gmail.com");
        Trader newTrader = registerService.createTrader(trader);
        registerService.deleteTrader(newTrader);
        return newTrader;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Trader createTraderJson(@RequestBody Trader trader) {
        try {
            return registerService.createTrader(trader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path = "/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}")
    public Trader createTrader(@PathVariable String firstname, @PathVariable String lastname,
                               @PathVariable String dob, @PathVariable String country,
                               @PathVariable String email) {
        Trader trader = new Trader();
        try {
            trader.setFirst_name(firstname);
            trader.setLast_name(lastname);
            trader.setDob(Date.valueOf(dob));
            trader.setCountry(country);
            trader.setEmail(email);
            return registerService.createTrader(trader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @PutMapping(path = "/deposit/accountId/{accountId}/amount/{amount}")
    public Account depositFunds(@PathVariable int accountId, @PathVariable double amount) {
        try {
            return fundTransferService.depositFunds(accountId, amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @PutMapping(path = "/withdraw/accountId/{accountId}/amount/{amount}")
    public Account withdrawFunds(@PathVariable int accountId, @PathVariable double amount) {
        try {
            return fundTransferService.withdrawFunds(accountId, amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
