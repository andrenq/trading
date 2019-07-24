package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.dto.TraderDto;
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

    public Trader createTrader(TraderDto traderDto) {
        logger.info(traderDto.toString());
        if (traderDto.getCountry().isEmpty() |
                traderDto.getDob().toString().isEmpty() |
                traderDto.getEmail().isEmpty() |
                traderDto.getFirst_name().isEmpty() |
                traderDto.getLast_name().isEmpty()) {
            throw new RuntimeException("To create a new Trader you have to input all necessary data");
        }

        Trader newTrader = new Trader();
        newTrader.setCountry(traderDto.getCountry());
        newTrader.setDob(traderDto.getDob());
        newTrader.setEmail(traderDto.getEmail());
        newTrader.setFirst_name(traderDto.getFirst_name());
        newTrader.setLast_name(traderDto.getLast_name());
        logger.info("Value of the trader Id: " + newTrader.getId());
        createAccount(traderDao.save(newTrader));
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
