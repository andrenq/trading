package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    private SecurityOrderDao securityOrderDao;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/marketorder", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public SecurityOrder marketOrder(@RequestBody SecurityOrder securityOrder) {
        try {
            return orderService.executeSecurityOrder(orderService.checkSecurityOrder(
                    orderService.createMarketOrderDTO(securityOrder)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
