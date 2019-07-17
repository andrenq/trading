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
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MarketDataDao {

    private HttpClientConnectionManager poolingConnManager;
    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager) {
        this.poolingConnManager = poolingConnManager;
    }

    private final String baseURL ="https://cloud.iexapis.com/stable/stock/market/batch?symbols=%s&types=quote&token=";
    private final String token = System.getenv("iEXToken");

    public List<IEXQuote> findIexQuoteByTicker(List<String> tickers)  {
        String tickersCombined =String.join(",",tickers);
        String uri = String.format(baseURL,tickersCombined)+token;
        List<IEXQuote> quotes = new ArrayList<>();
        try(CloseableHttpClient client = HttpClients.custom().setConnectionManager(poolingConnManager).build();){
            HttpResponse response = client.execute(new HttpGet(uri));
            if (response.getStatusLine().getStatusCode() == 200) {
                JSONObject iEXQuoteJson = new JSONObject(EntityUtils.toString(response.getEntity()));
                Gson g = new Gson();
                iEXQuoteJson.keys().forEachRemaining(ticker -> {
                    String quoteStr = ((JSONObject) iEXQuoteJson.get(ticker)).get("quote").toString();
                    IEXQuote iexQuote = g.fromJson(quoteStr, IEXQuote.class);
                    quotes.add(iexQuote);
                });
            }else throw new RuntimeException("Response error: "+ response.getStatusLine());
        }catch (IOException ex) {
                ex.printStackTrace();
            }

        return quotes;

    }
    public IEXQuote findIexQuoteByTicker(String ticker) throws IOException {
        return findIexQuoteByTicker(Arrays.asList(ticker)).get(0);

    }

}
