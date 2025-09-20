package io.github.sergejsvisockis.ecommerce.hub.store.service;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.BuyerReferenceRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Cart;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.CartBuyer;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.CartRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.exception.StoreException;
import io.github.sergejsvisockis.ecommerce.hub.store.mapper.CartMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public CartResponse createCart(CartRequest request) {
        Cart cart = cartMapper.toCart(request);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartItemsResponse(savedCart);
    }

    public void setBuyerReference(UUID cartId, BuyerReferenceRequest request) {

        CartBuyer cartBuyer = cartMapper.toCartItemBuyer(request);

        Optional<Cart> foundCart = cartRepository.findById(cartId);

        if (foundCart.isEmpty()) {
            throw new StoreException("Cart with id " + cartId + " not found");
        }

        Cart cart = foundCart.get();
        cart.setBuyer(cartBuyer);

        cartRepository.save(cart);

    }

}
