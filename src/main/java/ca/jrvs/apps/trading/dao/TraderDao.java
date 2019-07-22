package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderDao extends JpaRepository<Trader, String> {
    Optional<Trader> findByid(int id);

}

