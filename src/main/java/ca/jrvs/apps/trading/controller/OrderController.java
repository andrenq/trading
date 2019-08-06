package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/marketorder", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public SecurityOrder marketOrder(@RequestBody MarketOrderDto marketOrderDto) {
        try {
            return orderService.executeSecurityOrder(orderService.checkSecurityOrder(
                    orderService.createMarketOrderDTO(marketOrderDto)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
