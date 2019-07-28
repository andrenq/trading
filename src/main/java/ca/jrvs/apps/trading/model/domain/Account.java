package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @OneToMany
    @JoinColumn(name = "account_id", updatable = false)
    List<Position> positionList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int accountID;
    @Column(name = "trader_id")
    private int traderId;
    @Column(name = "amount")
    private double amount;

    public Account() {
    }

    public List<Position> getPosition() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
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

    @Override
    public String toString() {
        return "Account{" + "\n" +
                "accountID=" + accountID + "\n" +
                ", traderId=" + traderId + "\n" +
                ", amount=" + amount + "\n" +
                ", positionList=" + positionList + "\n" +
                '}';
    }

}
