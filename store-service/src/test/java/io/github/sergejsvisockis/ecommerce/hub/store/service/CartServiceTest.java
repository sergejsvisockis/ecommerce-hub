package io.github.sergejsvisockis.ecommerce.hub.store.service;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.BuyerReferenceRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Cart;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.CartBuyer;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.CartRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.exception.StoreException;
import io.github.sergejsvisockis.ecommerce.hub.store.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartService cartService;

    @Test
    void shouldReturnCartResponseWhenCreateCartWithValidRequest() {
        // given
        CartRequest request = new CartRequest();
        Cart cart = new Cart();
        when(cartMapper.toCart(request)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(cart);
        CartResponse response = new CartResponse().cartId(cart.getCartId());
        when(cartMapper.toCartItemsResponse(cart)).thenReturn(response);

        // when
        CartResponse result = cartService.createCart(request);

        // then
        assertNotNull(result);
        assertEquals(cart.getCartId(), result.getCartId());
        verify(cartMapper).toCart(request);
        verify(cartRepository).save(cart);
        verify(cartMapper).toCartItemsResponse(cart);
    }

    @Test
    void shouldSaveBuyerReferenceWhenSetBuyerReferenceWithExistingCart() {
        // given
        UUID cartId = UUID.randomUUID();
        BuyerReferenceRequest buyerRequest = new BuyerReferenceRequest()
                .firstName("John").lastName("Doe");
        CartBuyer buyer = new CartBuyer();
        Cart cart = new Cart();
        when(cartMapper.toCartItemBuyer(buyerRequest)).thenReturn(buyer);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        // when
        cartService.setBuyerReference(cartId, buyerRequest);

        // then
        verify(cartRepository).findById(cartId);
        verify(cartRepository).save(cart);
        assertSame(buyer, cart.getBuyer());
    }

    @Test
    void shouldThrowStoreExceptionWhenSetBuyerReferenceWithMissingCart() {
        // given
        UUID cartId = UUID.randomUUID();
        BuyerReferenceRequest buyerRequest = new BuyerReferenceRequest();
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        // when
        StoreException ex = assertThrows(StoreException.class,
                () -> cartService.setBuyerReference(cartId, buyerRequest));

        // then
        assertTrue(ex.getMessage().contains("Cart with id "));
        verify(cartRepository).findById(cartId);
        verify(cartRepository, never()).save(any());
    }
}
