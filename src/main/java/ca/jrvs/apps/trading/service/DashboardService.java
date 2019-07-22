package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class DashboardService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);
    private TraderDao traderDao;
    private AccountDao accountDao;

    @Autowired
    public DashboardService(TraderDao traderDao, AccountDao accountDao) {
        this.accountDao = accountDao;
        this.traderDao = traderDao;
    }

    @GetMapping(path = "/traderId/{traderId}")
    public String traderAccountsDetails(@PathVariable int traderId) {
        String response = "";
        response = traderDao.findByid(traderId).toString();
        response += accountDao.findAllBytraderId(traderId).toString();
        return response;
    }

}
