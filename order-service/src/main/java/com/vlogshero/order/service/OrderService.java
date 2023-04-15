package com.vlogshero.order.service;

import com.vlogshero.order.dto.InventoryResponse;
import com.vlogshero.order.dto.OrderLineItemDto;
import com.vlogshero.order.dto.OrderRequest;
import com.vlogshero.order.model.Order;
import com.vlogshero.order.model.OrderLineItem;
import com.vlogshero.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        /*
         *  Call Inventory service and check product in stock or not
         *  if product present in stock then place the order
         *   otherwise reject
         * **/
        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).toList();
        InventoryResponse[] results = webClientBuilder.build().get()
                .uri(
                        "http://localhost:8083/inventory-service/api-v1/inventory/skuCodesAvailability",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert results != null;
        boolean allProductsInStock = Arrays.stream(results).allMatch(InventoryResponse::isPresent);

        if (allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock. Please try again later.");
        }
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();

        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQty(orderLineItemDto.getQty());

        return orderLineItem;
    }
}
