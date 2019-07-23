package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
    Account findByAccountID(int id);

    void deleteBytraderId(int traderID);

    List<Account> findAllBytraderId(int traderID);
}
