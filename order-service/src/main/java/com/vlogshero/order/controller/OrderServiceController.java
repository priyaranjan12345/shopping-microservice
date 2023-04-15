package com.vlogshero.order.controller;

import com.vlogshero.order.dto.OrderRequest;
import com.vlogshero.order.dto.InfoDto;
import com.vlogshero.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-v1/product")
@RequiredArgsConstructor
public class OrderServiceController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoDto placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        InfoDto infoDto = new InfoDto();
        infoDto.setMessage("Order placed successfully");
        return infoDto;
    }
}
