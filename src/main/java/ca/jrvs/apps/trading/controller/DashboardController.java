package ca.jrvs.apps.trading.controller;


import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/portfolio")
public class DashboardController {
    private DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/traderId/{traderId}")
    public Trader traderAccountsDetails(@PathVariable int traderId) {
        return dashboardService.traderAccountsDetails(traderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/listall")
    public List<Trader> listAllTraders() {
        return dashboardService.listAllTraders();
    }

}
