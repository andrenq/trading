package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TradingApplication {

    @Bean
    public MarketDataDao marketDataDao(HttpClientConnectionManager httpClientConnectionManager) {
        return new MarketDataDao(httpClientConnectionManager);
    }

    @Bean
    HttpClientConnectionManager httpClientConnectionManager() {
        return new PoolingHttpClientConnectionManager();
    }

	public static void main(String[] args) {
		SpringApplication.run(TradingApplication.class, args);
	}

}
