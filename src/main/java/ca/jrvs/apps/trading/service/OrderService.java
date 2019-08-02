package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.*;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import ca.jrvs.apps.trading.model.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class OrderService {
    private SecurityOrderDao securityOrderDao;
    private QuoteDao quoteDao;
    private AccountDao accountDao;
    private FundTransferService fundTransferService;
    private PositionDao positionDao;

    @Autowired
    public OrderService(SecurityOrderDao securityOrderDao, QuoteDao quoteDao, AccountDao accountDao,
                        FundTransferService fundTransferService, PositionDao positionDao) {
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.accountDao = accountDao;
        this.fundTransferService = fundTransferService;
        this.positionDao = positionDao;
    }

    /**
     * Validates the input market order and
     * creates an OrderDto {
     * private Account account;
     * private SecurityOrder securityOrder;
     * private Quote quote;
     * private double totalCost;}
     *
     * @param marketOrderDto
     * @return OrderDto
     */
    public OrderDto createMarketOrderDTO(MarketOrderDto marketOrderDto) {
        SecurityOrder securityOrder = new SecurityOrder();
        try {
            securityOrder.setTicker(marketOrderDto.getTicker());
            securityOrder.setAccountID(marketOrderDto.getAccountId());
            securityOrder.setSize(marketOrderDto.getSize());
            securityOrder.setStatus(OrderStatus.PENDING);
            securityOrder.setTicker(securityOrder.getTicker().toUpperCase());
            Quote quote = quoteDao.findByTicker(securityOrder.getTicker());
            Account account = accountDao.findByAccountID(securityOrder.getAccountID());
            double totalCost;
            if (marketOrderDto.getSize() >= 0) {
                totalCost = securityOrder.getSize() * quote.getAsk_price();
            } else {
                totalCost = securityOrder.getSize() * quote.getBid_price();
            }
            securityOrderDao.save(securityOrder);
            OrderDto m = new OrderDto(account, securityOrder, quote, totalCost);
            return m;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Could not create order. Missing values, wrong ticker or wrong account." +
                    "Check inputted values.");
        }

    }

    /**
     * Validates the security order. Checks quantity of shares, available funds and ticker
     * If any problems are found, it changes the status of the security order to cancelled
     *
     * @param orderDto
     * @return OrderDto
     */

    public OrderDto checkSecurityOrder(OrderDto orderDto) {
        boolean checkSecOrder = false;
        try {           //buy order
            if (orderDto.getSecurityOrder().getSize() >= 0) {
                if ((orderDto.getAccount().getAmount() > orderDto.getTotalCost()) ||
                        (orderDto.getSecurityOrder().getTicker().equalsIgnoreCase(orderDto.getQuote().getTicker()) ||
                                (orderDto.getQuote().getAsk_size() > orderDto.getSecurityOrder().getSize()))) {
                    checkSecOrder = true;
                }
            } else {    //sell order
                Position position = positionDao.findPositionByAccountIDAndTicker(orderDto.getAccount().getAccountID(),
                        orderDto.getSecurityOrder().getTicker());
                if ((position.getTicker().equals(orderDto.getSecurityOrder().getTicker()))
                        && (position.getPosition() >= orderDto.getSecurityOrder().getSize() * -1)) //to allow short
                // positions, comment this line
                {
                    checkSecOrder = true;
                }

            }
        } catch (NullPointerException e) {
            orderDto.getSecurityOrder().setStatus(OrderStatus.CANCELLED);
            orderDto.getSecurityOrder().setNotes("Ticker is not on the database, or trader does not own this stock");
            securityOrderDao.save(orderDto.getSecurityOrder());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, orderDto.getSecurityOrder().getNotes());
        }
        if (!checkSecOrder) {
            orderDto.getSecurityOrder().setStatus(OrderStatus.CANCELLED);
            orderDto.getSecurityOrder().setNotes("Not enough funds or shares");
            securityOrderDao.save(orderDto.getSecurityOrder());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, orderDto.getSecurityOrder().getNotes());
        }
        return orderDto;
    }

    public SecurityOrder executeSecurityOrder(OrderDto m) {

        if (m.getSecurityOrder().getStatus().equals(OrderStatus.PENDING)) {
            if (m.getSecurityOrder().getSize() >= 0) {
                fundTransferService.withdrawFunds(m.getAccount().getAccountID(), m.getTotalCost());
            } else {
                fundTransferService.depositFunds(m.getAccount().getAccountID(), m.getTotalCost() * -1);
            }
            m.getSecurityOrder().setStatus(OrderStatus.FILLED);
            m.getSecurityOrder().setPrice(m.getQuote().getAsk_price());
        }
        return securityOrderDao.save(m.getSecurityOrder());
    }
}
