package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

    @Query(value = "DELETE FROM account a WHERE a.trader_id = ?1", nativeQuery = true)
    void delete(int traderID);

    void deleteByaccountID(int accountID);
    Account findByAccountID(int id);

    void deleteAllBytraderId(int traderID);

    List<Account> findALlByAccountID(int accountID);

    List<Account> findAllByTraderId(int traderID);

}

