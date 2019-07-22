package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
    Optional<Account> findById(String ticker);

    void deleteBytraderId(int traderID);

    List<Account> findAllBytraderId(int traderID);
}
