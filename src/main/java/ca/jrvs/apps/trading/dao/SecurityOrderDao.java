package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityOrderDao extends JpaRepository<SecurityOrder, String> {
    List<SecurityOrder> findAllByaccountID(int accountID);

    void deleteByAccountID(int accountID);
}
