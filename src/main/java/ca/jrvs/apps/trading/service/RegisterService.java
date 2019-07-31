package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.dto.TraderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.MissingFormatArgumentException;


@Transactional
@Service
public class RegisterService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
    private TraderDao traderDao;
    private AccountDao accountDao;
    private SecurityOrderDao securityOrderDao;

    @Autowired
    public RegisterService(TraderDao traderDao, AccountDao accountDao, SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
    }

    public Trader createTrader(TraderDto traderDto) {
        logger.info(traderDto.toString());
        Trader newTrader = new Trader();
        try {
            if (!(traderDto.getCountry().isEmpty() |
                    traderDto.getDob().toString().isEmpty() |
                    traderDto.getEmail().isEmpty() |
                    traderDto.getFirst_name().isEmpty() |
                    traderDto.getLast_name().isEmpty())) {
                newTrader.setCountry(traderDto.getCountry());
                newTrader.setDob(traderDto.getDob());
                newTrader.setEmail(traderDto.getEmail());
                newTrader.setFirst_name(traderDto.getFirst_name());
                newTrader.setLast_name(traderDto.getLast_name());
                logger.info("Value of the trader Id: " + newTrader.getId());
            }
        } catch (Exception e) {
            throw new MissingFormatArgumentException("To create a new Trader you have to input all necessary data");
        }
        createAccount(traderDao.save(newTrader));
        return newTrader;
    }

    public Account createAccount(Trader trader) {
        Account account = new Account();
        account.setTraderId(trader.getId());
        account.setAmount(0);
        return accountDao.save(account);
    }

    /**
     * DELETE
     * Delete -------------
     *
     * @param
     */

    public void deleteTrader(int traderid) {
        Trader trader = traderDao.findByid(traderid);
        if (trader == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Trader ID was not found");
        }
        deleteSecurityOrder(traderid);
        deleteAccount(traderid);
        //trader.getAccount().clear();
        traderDao.delete(trader);
    }

    public void deleteSecurityOrder(int traderID) {
        Trader trader = traderDao.findByid(traderID);
        for (Account ac : trader.getAccount()) {
            if (!ac.getPosition().isEmpty()) {
                securityOrderDao.deleteByAccountID(ac.getAccountID());
            }
        }
    }

    public void deleteAccount(int traderID) {
        accountDao.deleteAll(traderDao.findByid(traderID).getAccount());
    }
}
