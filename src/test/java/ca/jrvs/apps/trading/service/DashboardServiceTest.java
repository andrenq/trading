package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DashboardServiceTest {

    @Mock
    static TraderDao traderDao;

    static DashboardService dashboardService;

    Trader trader;
    Account account;
    List<Position> positions;
    Quote quote;


    @BeforeClass
    public static void setUp() {
        traderDao = mock(TraderDao.class);
        dashboardService = new DashboardService(traderDao);
    }

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
        account.setAmount(100);
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
    }

    @Test
    public void traderAccountsDetails() {
        when(traderDao.findByid(any())).thenReturn(trader);
        assertNotNull(dashboardService.traderAccountsDetails(1));
    }

    @Test
    public void listAllTraders() {
    }
}