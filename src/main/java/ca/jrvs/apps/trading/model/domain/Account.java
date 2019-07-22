package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private double accountID;
    @Column(name = "trader_id")
    private double traderID;
    @Column(name = "amount")
    private double amount;

    public double getAccountID() {
        return accountID;
    }

    public void setAccountID(double accountID) {
        this.accountID = accountID;
    }

    public double getTraderID() {
        return traderID;
    }

    public void setTraderID(double traderID) {
        this.traderID = traderID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
