package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.*;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    static TraderDao traderDao;
    @InjectMocks
    static OrderService orderService;
    Trader trader;
    Account account;
    List<Position> positions;
    Quote quote;
    MarketOrderDto marketOrderDto;
    SecurityOrder securityOrder;
    @Mock
    private SecurityOrderDao securityOrderDao;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private AccountDao accountDao;
    @Mock
    private PositionDao positionDao;
    @InjectMocks
    private FundTransferService fundTransferService;

    @Before
    public void before() throws ParseException {
        quote = new Quote();
        quote.setTicker("VALE");
        quote.setAsk_price(10.0);
        quote.setBid_price(10.0);

        positions = new ArrayList<>();
        Position position = new Position();
        position.setAccountID(1);
        position.setPosition(100);
        position.setTicker("VALE");
        positions.add(position);

        account = new Account();
        account.setAccountID(1);
        account.setTraderId(1);
        account.setAmount(1000000);
        account.setPositionList(positions);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = formatter.parse("1984-01-01");
        trader = new Trader();
        trader.setDob(dob);
        trader.setCountry("USA");
        trader.setLast_name("Hefner");
        trader.setFirst_name("Hugh");
        trader.setEmail("test@gmail.com");
        trader.setAccList(accounts);
        trader.setId(1);

        securityOrder = new SecurityOrder();
        securityOrder.setTicker("VALE");
        securityOrder.setAccountID(1);
        securityOrder.setSize(100);
        securityOrder.setStatus(OrderStatus.PENDING);

        marketOrderDto = new MarketOrderDto(account.getAccountID(), position.getPosition(), position.getTicker());

        when(quoteDao.findByTicker("VALE")).thenReturn(quote);
        when(accountDao.findByAccountID(marketOrderDto.getAccountId())).thenReturn(account);
        when(securityOrderDao.save(any())).thenReturn(any());
    }

    @Test
    public void createMarketOrderDTOTest() {
        assertEquals(account, orderService.createMarketOrderDTO(marketOrderDto).getAccount());
        assertEquals(securityOrder.getTicker(), orderService.createMarketOrderDTO(marketOrderDto).getSecurityOrder().getTicker());
        assertEquals(quote, orderService.createMarketOrderDTO(marketOrderDto).getQuote());
    }

    @Test
    public void checkSecurityOrderTest() {
        assertEquals(OrderStatus.PENDING, orderService.checkSecurityOrder(orderService.createMarketOrderDTO(marketOrderDto)).
                getSecurityOrder().getStatus());
    }

}