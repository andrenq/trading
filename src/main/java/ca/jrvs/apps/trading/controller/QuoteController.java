package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IEXQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);
    @Autowired
    QuoteDao quoteDao;
    @Autowired
    MarketDataDao marketDataDao;

    /**
     * Recieves a coma separated string with tickers
     *
     * @param ticker
     * @return List of IEXQuote objects
     */
    @GetMapping(path = "/iex/ticker/{ticker}")
    public List<IEXQuote> getQuote(@PathVariable String ticker) {
        try {
            return marketDataDao.findIexQuoteByTicker(ticker);
        } catch (Exception e) {
            throw new RuntimeException("Error" + e);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/iex/ticker/{tickers}")
    public List<Quote> putQuote(@PathVariable String tickers) {
        List<Quote> quotes = new ArrayList<>();
        try {
            List<IEXQuote> tickerList = marketDataDao.findIexQuoteByTicker(tickers);
            quotes = iEXQuoteToQuote(tickerList);
            return quoteDao.saveAll(quotes);
        } catch (IOException e) {
            throw new RuntimeException("Error" + e);
        }
    }

    /**
     * Updates quotes (from IEX API) for all tickers on the database
     *
     * @return
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/dailyList")
    public List<Quote> getDailyQuote() {
        List<String> tickers = new ArrayList<>();
        quoteDao.findAll().stream().forEach(i -> tickers.add(i.getTicker()));
        List<IEXQuote> tickerList = marketDataDao.findIexQuoteByTicker(tickers);
        List<Quote> quotes = iEXQuoteToQuote(tickerList);
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
                    ticker.getIexAskPrice(),
                    ticker.getIexAskSize()));
        }
        return quotes;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Quote updateQuote(@RequestBody Quote quote) {
        try {
            logger.info(quote.toString());
            return quoteDao.save(quote);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
