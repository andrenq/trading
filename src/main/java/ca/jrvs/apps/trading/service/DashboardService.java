package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional
@Service
public class DashboardService {

    private TraderDao traderDao;


    @Autowired
    public DashboardService(TraderDao traderDao) {
        this.traderDao = traderDao;
    }

    public Trader traderAccountsDetails(int traderId) {
        Trader newTrader = traderDao.findByid(traderId);
        if (newTrader == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Trader ID was not found");
        }
        return traderDao.findByid(traderId);
    }

    public List<Trader> listAllTraders() {
        return traderDao.findAll();
    }
}
