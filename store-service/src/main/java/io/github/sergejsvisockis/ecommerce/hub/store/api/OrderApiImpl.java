package io.github.sergejsvisockis.ecommerce.hub.store.api;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderApiImpl implements OrderApi {

    private final OrderService orderService;

    public OrderApiImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        return new ResponseEntity<>(
                orderService.createOrder(orderRequest),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(UUID orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
}
