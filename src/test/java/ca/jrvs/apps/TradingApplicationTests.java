package ca.jrvs.apps;

import ca.jrvs.apps.trading.controller.AppController;
import ca.jrvs.apps.trading.controller.DashboardController;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.service.DashboardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class TradingApplicationTests {

    @Autowired
    static DashboardService dashboardService;
    @Autowired
    static PositionDao positionDao;

    static TraderDao traderDao;

    static AppController appController;
    static DashboardController dashboardController;

    @Before
    public void setUp() {
        dashboardService = new DashboardService(traderDao);
        appController = new AppController();
        dashboardController = new DashboardController(dashboardService, positionDao);
    }
    @Test
    public void appControllerTest() {
        assertEquals(appController.appController(), "I'm alive!");
    }

    @Test
    public void DashboardControllerTest() {
        assertNotNull(dashboardController.listAllTraders());
    }

}
