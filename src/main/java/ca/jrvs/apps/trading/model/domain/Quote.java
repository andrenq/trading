
package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @Basic(optional = false)
    @Column(name = "ticker", unique = true, nullable = false)
    private String ticker;
    @Column(name = "last_price")
    private double last_price;
    @Column(name = "bid_price")
    private double bid_price;
    @Column(name = "bid_size")
    private double bid_size;
    @Column(name = "ask_price")
    private double ask_price;
    @Column(name = "ask_size")
    private double ask_size;

    public Quote() {
    }

    public Quote(String ticker, double last_price, double bid_price, double ask_price, double ask_size) {
        this.ticker = ticker;
        this.last_price = last_price;
        this.bid_price = bid_price;
        this.ask_price = ask_price;
        this.ask_size = ask_size;
    }

    public double getBid_size() {
        return bid_size;
    }

    public void setBid_size(double bid_size) {
        this.bid_size = bid_size;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getLast_price() {
        return last_price;
    }

    public void setLast_price(double last_price) {
        this.last_price = last_price;
    }

    public double getBid_price() {
        return bid_price;
    }

    public void setBid_price(double bid_price) {
        this.bid_price = bid_price;
    }

    public double getAsk_price() {
        return ask_price;
    }

    public void setAsk_price(double ask_price) {
        this.ask_price = ask_price;
    }

    public double getAsk_size() {
        return ask_size;
    }

    public void setAsk_size(double ask_size) {
        this.ask_size = ask_size;
    }
}
