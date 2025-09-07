package io.github.sergejsvisockis.ecommerce.hub.store.api;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderApiImpl implements OrderApi {
    @Override
    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ResponseEntity<OrderResponse> getOrderById(UUID orderId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
