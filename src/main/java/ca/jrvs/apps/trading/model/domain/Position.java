package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @Column(name = "row_id")
    private int rowID;
    @Column(name = "account_id", nullable = false)
    private int accountID;
    @Column(name = "ticker")
    private String ticker;
    @Column(name = "position")
    private int position;

    public Position() {
    }

    public Position(int accountID, String ticker, int position) {
        this.accountID = accountID;
        this.ticker = ticker;
        this.position = position;
    }

    public double getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
