package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class FundTransferService {
    @Autowired
    AccountDao accountDao;

    public Account depositFunds(int accountId, double amount) {
        if (amount < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Amount for deposit mus be positive.");
        }
        if (accountDao.findByAccountID(accountId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account ID was not found");
        }
        Account account = accountDao.findByAccountID(accountId);
        account.setAmount(account.getAmount() + amount);
        return accountDao.save(account);
    }

    public Account withdrawFunds(int accountId, double amount) {
        if (amount < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Amount for withdraw mus be positive.");
        }
        Account account = accountDao.findByAccountID(accountId);
        if (account.getAmount() >= amount) {
            account.setAmount(account.getAmount() - amount);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Not enough funds: " +
                    " Withdraw attempt: $" + amount +
                    " Account balance: $" + account.getAmount());
        }
        return accountDao.save(account);
    }
}
