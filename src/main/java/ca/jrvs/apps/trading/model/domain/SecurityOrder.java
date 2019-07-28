package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "security_order")
public class SecurityOrder {
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private int securityOrderId;
    @Column(name = "account_id")
    private int accountID;
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "ticker")
    private String ticker;
    @Column(name = "size")
    private int size;
    @Column(name = "price")
    private double price;
    @Column(name = "notes")
    private String notes;

    public SecurityOrder() {
    }

    public SecurityOrder(int accountID, String ticker, int size) {
        this.accountID = accountID;
        this.ticker = ticker;
        this.size = size;
    }

    public SecurityOrder(int accountID, OrderStatus status, String ticker, int size, double price, String notes) {
        this.accountID = accountID;
        this.status = status;
        this.ticker = ticker;
        this.size = size;
        this.price = price;
        this.notes = notes;
    }

    public double getSecurityOrderId() {
        return securityOrderId;
    }

    public void setSecurityOrderId(int securityOrderId) {
        this.securityOrderId = securityOrderId;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
