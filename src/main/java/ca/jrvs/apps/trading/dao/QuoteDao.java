package ca.jrvs.apps.trading.dao;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuoteDao extends JpaRepository<Quote, String> {
    Optional<Quote> findById(String ticker);
}
