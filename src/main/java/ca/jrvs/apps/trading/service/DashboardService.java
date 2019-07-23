package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class DashboardService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);
    private TraderDao traderDao;
    private AccountDao accountDao;

    @Autowired
    public DashboardService(TraderDao traderDao, AccountDao accountDao) {
        this.accountDao = accountDao;
        this.traderDao = traderDao;
    }


    public Trader traderAccountsDetails(int traderId) {
        String response = "";
        return traderDao.findByid(traderId);
    }

}
