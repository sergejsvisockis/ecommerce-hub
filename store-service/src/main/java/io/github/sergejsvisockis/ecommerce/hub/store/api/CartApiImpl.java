package io.github.sergejsvisockis.ecommerce.hub.store.api;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.BuyerReferenceRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartApiImpl implements CartApi {

    private final CartService cartService;

    public CartApiImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<CartResponse> createCart(CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.createCart(cartRequest));
    }

    @Override
    public ResponseEntity<Void> setBuyerReference(UUID cartId, BuyerReferenceRequest buyerReferenceRequest) {
        cartService.setBuyerReference(cartId, buyerReferenceRequest);
        return ResponseEntity
                .accepted()
                .build();
    }
}
