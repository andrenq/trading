package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FundTransferService {
    @Autowired
    AccountDao accountDao;

    public Account depositFunds(int accountId, double amount) {
        Account account = accountDao.findByAccountID(accountId);
        account.setAmount(account.getAmount() + amount);
        return accountDao.save(account);
    }

    public Account withdrawFunds(int accountId, double amount) {
        Account account = accountDao.findByAccountID(accountId);
        if (account.getAmount() >= amount) {
            account.setAmount(account.getAmount() - amount);
        } else { //TODO fix error handling
            throw new RuntimeException("\nNot enough funds:\n" +
                    "Withdraw attempt: $" + amount + "\n" +
                    "Account balance: $" + account.getAmount() + "\n");
        }

        return accountDao.save(account);
    }
}
