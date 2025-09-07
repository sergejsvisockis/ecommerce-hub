package io.github.sergejsvisockis.ecommerce.hub.store.api;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.BuyerReferenceRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartApiImpl implements CartApi {

    @Override
    public ResponseEntity<CartResponse> createCart(CartRequest cartRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ResponseEntity<Void> setBuyerReference(UUID cartId, BuyerReferenceRequest buyerReferenceRequest) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
