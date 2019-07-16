package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import com.google.gson.Gson;
import io.micrometer.core.instrument.util.JsonUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.kopitubruk.util.json.JSONUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class MarketDataDao {

    private HttpClientConnectionManager poolingConnManager;
    private final String baseURL ="https://cloud.iexapis.com/stable/stock/market/batch?symbols=";
    private final String endURL = "&types=quote&token=";
    private final String token = System.getenv("iEXToken");
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager) {
        this.poolingConnManager = poolingConnManager;
    }


    public List<Quote> findIexQuoteByTicker(List<String> tickers) throws IOException {
        String tickersCombined =String.join(",",tickers);
        String uri = baseURL+tickersCombined+endURL+token;
        System.out.println(uri);
        HttpClient client = HttpClients.custom().setConnectionManager(poolingConnManager).build();
        HttpResponse response = client.execute(new HttpGet(uri));
        JSONObject iEXQuoteJson = new JSONObject(EntityUtils.toString(response.getEntity()));
        Gson g = new Gson();
        List<Quote> quotes = new ArrayList<>();
        iEXQuoteJson.keys().forEachRemaining(ticker->{
                String quoteStr = ((JSONObject) iEXQuoteJson.get(ticker)).get("quote").toString();
                Quote iexQuote = g.fromJson(quoteStr,Quote.class);
                quotes.add(iexQuote);
                });
        return quotes;

    }
    public Quote findIexQuoteByTicker(String ticker) throws IOException {
        return findIexQuoteByTicker(Arrays.asList(ticker)).get(0);

    }

}
