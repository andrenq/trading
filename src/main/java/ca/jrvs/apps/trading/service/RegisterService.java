package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.dto.TraderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    /**
     * CREATE
     */
    public Trader createTrader(TraderDto traderDto) {
        logger.info(traderDto.toString());
        Trader newTrader = new Trader();
        try {
            if (!(traderDto.getCountry().isEmpty() &&
                    traderDto.getDob().toString().isEmpty() &&
                    traderDto.getEmail().isEmpty() &&
                    traderDto.getFirst_name().isEmpty() &&
                    traderDto.getLast_name().isEmpty())) {
                newTrader.setCountry(String.valueOf(traderDto.getCountry()));
                newTrader.setDob((traderDto.getDob()));
                newTrader.setEmail(String.valueOf(traderDto.getEmail()));
                newTrader.setFirst_name(String.valueOf(traderDto.getFirst_name()));
                newTrader.setLast_name(String.valueOf(traderDto.getLast_name()));
                logger.info("Value of the trader Id: " + newTrader.getId());
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "To create a new Trader, you have to input all fields and they must have " +
                    "the right format. Date format should be YYYY-MM-DD");
        }
        createAccount(traderDao.save(newTrader));
        return newTrader;
    }

    public Account createAccount(Trader trader) {
        try {
            Account account = new Account();
            account.setTraderId(trader.getId());
            account.setAmount(0);
            return accountDao.save(account);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Account number already exists, keep trying until a valid account number " +
                    "is generated.");
        }
    }

    /**
     * DELETE trader
     * Delete -------------
     */

    public void deleteTrader(int traderid) {
        Trader trader = traderDao.findByid(traderid);
        if (trader == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Trader ID was not found");
        }
        deleteSecurityOrder(traderid);
        deleteAccount(traderid);
        traderDao.delete(trader);
    }


    public void deleteSecurityOrder(int traderID) {
        Trader trader = traderDao.findByid(traderID);
        for (Account ac : trader.getAccount()) {
            if (!ac.getPosition().isEmpty()) {
                for (Position p : ac.getPosition()) {
                    if (p.getPosition() != 0) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Trader has open positions");
                    }
                }
                securityOrderDao.deleteByAccountID(ac.getAccountID());
            }
        }
    }

    public void deleteAccount(int traderID) {
        Trader trader = traderDao.findByid(traderID);
        for (Account ac : trader.getAccount()) {
            if (ac.getAmount() != 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Trader non zero accounts");
            }
        }
        accountDao.deleteAll(traderDao.findByid(traderID).getAccount());
    }
}
