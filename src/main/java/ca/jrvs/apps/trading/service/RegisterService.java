package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RegisterService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
    private TraderDao traderDao;
    private AccountDao accountDao;

    @Autowired
    public RegisterService(TraderDao traderDao, AccountDao accountDao) {
        this.accountDao = accountDao;
        this.traderDao = traderDao;
    }

    public Trader createTrader(Trader trader) {
        logger.info(trader.toString());
        if (trader.getCountry().isEmpty() |
                trader.getDob().toString().isEmpty() |
                trader.getEmail().isEmpty() |
                trader.getFirst_name().isEmpty() |
                trader.getLast_name().isEmpty()) {
            throw new RuntimeException("To create a new Trader you have to input all necessary data:{\n" +
                    "  \"country\": \"string\",\n" +
                    "  \"dob\": \"2019-07-22T14:38:01.444Z\",\n" +
                    "  \"email\": \"string\",\n" +
                    "  \"firstName\": \"string\",\n" +
                    "  \"id\": 0,\n" +
                    "  \"lastName\": \"string\"\n" +
                    "}");
        }
        Trader newTrader = traderDao.save(trader);
        logger.info("Value of the trader Id: " + newTrader.getId());
        createAccount(newTrader);
        return newTrader;
    }


    public Account createAccount(Trader trader) {
        Account account = new Account();
        account.setTraderId(trader.getId());
        account.setAmount(0);
        return accountDao.save(account);
    }

    public void deleteTrader(Trader trader) {
        accountDao.deleteBytraderId(trader.getId());
        traderDao.delete(trader);
    }
}
