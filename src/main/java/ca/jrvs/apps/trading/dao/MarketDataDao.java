package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.IEXQuote;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MarketDataDao {
    //TODO create MarketDaoConfig
    private final String BASE_URL = "https://cloud.iexapis.com/stable/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_TOKEN = System.getenv("IEX_TOKEN");
    private HttpClientConnectionManager poolingConnManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager) {
        this.poolingConnManager = poolingConnManager;
    }

    public List<IEXQuote> findIexQuoteByTicker(List<String> tickers) {
        String tickersCombined = String.join(",", tickers);
        String uri = String.format(BASE_URL, tickersCombined) + IEX_TOKEN;
        List<IEXQuote> quotes = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.custom().setConnectionManager(poolingConnManager).build()) {
            HttpResponse response = client.execute(new HttpGet(uri));
            if (response.getStatusLine().getStatusCode() == 200) {
                JSONObject iEXQuoteJson = new JSONObject(EntityUtils.toString(response.getEntity()));
                Gson g = new Gson();
                iEXQuoteJson.keys().forEachRemaining(ticker -> {
                    String quoteStr = ((JSONObject) iEXQuoteJson.get(ticker)).get("quote").toString();
                    IEXQuote iexQuote = g.fromJson(quoteStr, IEXQuote.class);
                    quotes.add(iexQuote);
                });
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, response.getStatusLine().toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return quotes;
    }

    public List<IEXQuote> findIexQuoteByTicker(String ticker) {
        try {
            return findIexQuoteByTicker(Arrays.asList(ticker));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());

        }
    }

}
