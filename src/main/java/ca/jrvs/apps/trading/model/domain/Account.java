package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int accountID;
    @Column(name = "trader_id")
    private int traderId;
    @Column(name = "amount")
    private double amount;

    //Trader trader;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    List<Position> positionList = new ArrayList<>();

    public List<Position> getsecurityOrder() {
        return positionList;
    }

    public void setsecurityOrderList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    public String toString() {
        return "Account{" + "\n" +
                "accountID=" + accountID + "\n" +
                ", traderId=" + traderId + "\n" +
                ", amount=" + amount + "\n" +
                '}';
    }

    public Account() {
    }

    public Account(int traderId, double amount) {
        this.traderId = traderId;
        this.amount = amount;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name="traderId",referencedColumnName="traderId")
//    public Trader getTrader() {
//        return trader;
//    }
//    public void setTrader(Trader trader) {
//        this.trader = trader;
//    }
}
