package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.IEXQuote;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

@SpringBootTest
public class MarketDataDaoTest {
    HttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
    MarketDataDao marketDataDao = new MarketDataDao(poolingConnManager);

    @Test
    public void findIexQuoteByTickerTest() throws IOException, JSONException {
        List<String> tickers = Arrays.asList("AAPL", "FB", "MT");
        List<IEXQuote> quotes = marketDataDao.findIexQuoteByTicker(tickers);
        assertNotNull(quotes);
        try {
            quotes = marketDataDao.findIexQuoteByTicker(Arrays.asList("", "", ""));
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void findIexQuoteByTickerSingleTest() throws IOException, JSONException {
        String ticker  = "AAPL";
        List<IEXQuote> quote = marketDataDao.findIexQuoteByTicker(ticker);
    }
}