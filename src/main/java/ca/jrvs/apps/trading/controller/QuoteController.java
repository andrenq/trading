package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IEXQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);
    @Autowired
    QuoteDao quoteDao;
    @Autowired
    MarketDataDao marketDataDao;
    @Autowired
    QuoteService quoteService;

    /**
     * Updates quotes (from IEX API) for all tickers on the database
     *
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/iexMarketData")
    public List<Quote> postIEXUpdateQuote() {
        try {
            return quoteService.postIexUpdateQuote();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Receives a JSON file and saves as a Quote
     *
     * @param quote
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Quote updateQuote(@RequestBody Quote quote) {
        try {
            quote.setCreated_at(new Timestamp(System.currentTimeMillis()));
            return quoteDao.save(quote);
        } catch (Exception e) {
            throw e;
        }
    }

    //Get ticker, update quote on IEX , save to DB
    @PostMapping(path = "/ticker/{tickers}")
    public List<Quote> putQuote(@PathVariable String tickers) {
        try {
            return quoteService.putQuote(tickers);
        } catch (IOException e) {
            throw new RuntimeException("Error" + e);
        }
    }

    /**
     * Updates quotes (from IEX API) for all tickers on the database
     *
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/dailylist")
    public List<Quote> getDailyQuote() {
        try {
            return quoteDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error " + e);
        }
    }

    /**
     * Recieves a coma separated string with tickers
     *
     * @param tickers
     * @return List of IEXQuote objects
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/iex/ticker/{tickers}")
    public List<IEXQuote> getQuote(@PathVariable String tickers) {
        try {
            return marketDataDao.findIexQuoteByTicker(tickers);
        } catch (Exception e) {
            throw e;
        }
    }


}
