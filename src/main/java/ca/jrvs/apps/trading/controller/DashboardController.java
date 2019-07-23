package ca.jrvs.apps.trading.controller;


import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class DashboardController {

    private DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping(path = "/traderId/{traderId}")
    public Trader traderAccountsDetails(@PathVariable int traderId) {
        return dashboardService.traderAccountsDetails(traderId);
    }

}
