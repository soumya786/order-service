package com.soumya.microservies.order_service.service;

import com.soumya.microservies.order_service.client.InventoryClient;
import com.soumya.microservies.order_service.dto.OrderRequest;
import com.soumya.microservies.order_service.model.Order;
import com.soumya.microservies.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with SKU Code " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
