package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.OrderStatus;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderService {
    private SecurityOrderDao securityOrderDao;
    private QuoteDao quoteDao;
    private AccountDao accountDao;
    private FundTransferService fundTransferService;

    @Autowired
    public OrderService(SecurityOrderDao securityOrderDao, QuoteDao quoteDao,
                        AccountDao accountDao, FundTransferService fundTransferService) {
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.accountDao = accountDao;
        this.fundTransferService = fundTransferService;
    }

    public OrderDto createMarketOrderDTO(MarketOrderDto marketOrderDto) {
        SecurityOrder securityOrder = new SecurityOrder();
        securityOrder.setTicker(marketOrderDto.getTicker());
        securityOrder.setAccount_id(marketOrderDto.getAccountId());
        securityOrder.setSize(marketOrderDto.getSize());
        securityOrder.setStatus(OrderStatus.PENDING);
        securityOrder.setTicker(securityOrder.getTicker().toUpperCase());
        Quote quote = quoteDao.findByTicker(securityOrder.getTicker());
        Account account = accountDao.findByAccountID(securityOrder.getAccount_id());
        double totalCost = securityOrder.getSize() * quote.getAsk_price();
        OrderDto m = new OrderDto(account, securityOrder, quote, totalCost);
        securityOrderDao.save(securityOrder);
        return m;
    }

    public OrderDto checkSecurityOrder(OrderDto m) {
        try {
            if ((m.getAccount().getAmount() < m.getTotalCost()) ||
                    (!m.getSecurityOrder().getTicker().equalsIgnoreCase(m.getQuote().getTicker()) ||
                            (m.getQuote().getAsk_size() < m.getSecurityOrder().getSize()))) {
                m.getSecurityOrder().setStatus(OrderStatus.CANCELLED);
                m.getSecurityOrder().setNotes("Not enough funds or shares");
            }
        } catch (NullPointerException e) {
            m.getSecurityOrder().setStatus(OrderStatus.CANCELLED);
            m.getSecurityOrder().setNotes("Ticker is not on the database");

        }
        securityOrderDao.save(m.getSecurityOrder());
        return m;
    }

    public SecurityOrder executeSecurityOrder(OrderDto m) {
        if (m.getSecurityOrder().getStatus().equals(OrderStatus.PENDING)) {
            fundTransferService.withdrawFunds(m.getAccount().getAccountID(), m.getTotalCost());
            m.getSecurityOrder().setStatus(OrderStatus.FILLED);
            m.getSecurityOrder().setPrice(m.getQuote().getAsk_price());
        }
        return securityOrderDao.save(m.getSecurityOrder());
    }

}
