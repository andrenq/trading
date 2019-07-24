package ca.jrvs.apps.trading.controller;


import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class DashboardController {

    private DashboardService dashboardService;
    private PositionDao positionDao;

    @Autowired
    public DashboardController(DashboardService dashboardService, PositionDao positionDao) {
        this.dashboardService = dashboardService;
        this.positionDao = positionDao;
    }

    @GetMapping(path = "/traderId/{traderId}")
    public Trader traderAccountsDetails(@PathVariable int traderId) {
        return dashboardService.traderAccountsDetails(traderId);
    }

    @GetMapping(path = "/position/{accountID}")
    public List<Position> positionDetails(@PathVariable int accountID) {
        return positionDao.findAllByaccountID(accountID);
    }

}
