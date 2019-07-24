package ca.jrvs.apps.trading.model.dto;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;

public class OrderDto {
    private Account account;
    private SecurityOrder securityOrder;
    private Quote quote;
    private double totalCost;

    public OrderDto(Account account, SecurityOrder securityOrder, Quote quote, double totalCost) {
        this.account = account;
        this.securityOrder = securityOrder;
        this.quote = quote;
        this.totalCost = totalCost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SecurityOrder getSecurityOrder() {
        return securityOrder;
    }

    public void setSecurityOrder(SecurityOrder securityOrder) {
        this.securityOrder = securityOrder;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
