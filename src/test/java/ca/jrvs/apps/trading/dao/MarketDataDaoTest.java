package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.json.JSONException;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MarketDataDaoTest {
    HttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
    MarketDataDao marketDataDao = new MarketDataDao(poolingConnManager);
    @Test
    public void findIexQuoteByTickerTest() throws IOException, JSONException {
        List<String> tickers  = Arrays.asList("AAPL","FB");
        List<Quote> quotes = marketDataDao.findIexQuoteByTicker(tickers);
        System.out.println(quotes);
    }

    @Test
    public void findIexQuoteByTickerSingleTest() throws IOException, JSONException {
        String ticker  = "AAPL";
        Quote quote = marketDataDao.findIexQuoteByTicker(ticker);
        System.out.println(quote);
    }
}