package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IEXQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);
    private MarketDataDao marketDataDao;
    private QuoteDao quoteDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Gets a list of tickers, quotes from IEX, save it on database
     *
     * @param tickers
     * @return
     * @throws IOException
     */
    public List<Quote> putQuote(String tickers) throws IOException {
        List<Quote> quotes;
        List<IEXQuote> tickerList = marketDataDao.findIexQuoteByTicker(tickers);
        quotes = iEXQuoteToQuote(tickerList);
        return quoteDao.saveAll(quotes);
    }

    /**
     * Converts IEXQuotes to Quotes
     *
     * @param iexQuotes Recives a list of IEXQuote Objects and
     * @return list of Quote Objects
     */
    public List<Quote> iEXQuoteToQuote(List<IEXQuote> iexQuotes) {
        List<Quote> quotes = new ArrayList<>();
        for (IEXQuote ticker : iexQuotes
        ) {
            quotes.add(new Quote(ticker.getSymbol(),
                    ticker.getLatestPrice(),
                    ticker.getIexBidPrice(),
                    ticker.getIexBidSize(),
                    ticker.getIexAskPrice(),
                    ticker.getIexAskSize(),
                    new Timestamp(System.currentTimeMillis())));

        }
        return quotes;
    }

    public List<Quote> postIexUpdateQuote() {
        List<String> tickers = new ArrayList<>();
        quoteDao.findAll().stream().forEach(i -> tickers.add(i.getTicker()));
        List<IEXQuote> tickerList = marketDataDao.findIexQuoteByTicker(tickers);
        List<Quote> quotes = iEXQuoteToQuote(tickerList);
        return quoteDao.saveAll(quotes);
    }
}
