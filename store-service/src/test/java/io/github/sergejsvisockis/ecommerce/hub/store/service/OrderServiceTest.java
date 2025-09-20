package io.github.sergejsvisockis.ecommerce.hub.store.service;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Cart;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.CartBuyer;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Order;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.OrderStatus;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.CartRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.OrderRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.exception.StoreException;
import io.github.sergejsvisockis.ecommerce.hub.store.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private UUID cartId;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        cartId = UUID.randomUUID();
        orderId = UUID.randomUUID();
    }

    @Test
    void shouldReturnOrderResponseWhenCreateOrderWithValidCartId() {
        // given
        OrderRequest request = new OrderRequest();
        request.setCartId(cartId);
        Cart cart = new Cart();
        cart.setBuyer(new CartBuyer());
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        Order saved = new Order();
        saved.setOrderId(orderId);
        saved.setStatus(OrderStatus.PLACED);
        saved.setOrderDate(LocalDateTime.now());
        when(orderRepository.save(any(Order.class))).thenReturn(saved);

        OrderResponse mapped = new OrderResponse()
                .orderId(orderId)
                .status(io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderStatus.PLACED)
                .orderDate(saved.getOrderDate());
        when(orderMapper.toOrderResponse(saved)).thenReturn(mapped);

        // when
        OrderResponse response = orderService.createOrder(request);

        // then
        assertNotNull(response);
        assertEquals(orderId, response.getOrderId());
        verify(cartRepository).findById(cartId);
        verify(orderRepository).save(any(Order.class));
        verify(orderMapper).toOrderResponse(saved);
    }

    @Test
    void shouldThrowStoreExceptionWhenCreateOrderWithNullCartId() {
        // given
        OrderRequest request = new OrderRequest();
        request.setCartId(null);

        // when
        StoreException ex = assertThrows(StoreException.class, () -> orderService.createOrder(request));

        // then
        assertTrue(ex.getMessage().contains("CartId is required"));
        verifyNoInteractions(cartRepository, orderRepository, orderMapper);
    }

    @Test
    void shouldThrowStoreExceptionWhenCreateOrderWithMissingCart() {
        // given
        OrderRequest request = new OrderRequest();
        request.setCartId(cartId);
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        // when
        StoreException ex = assertThrows(StoreException.class, () -> orderService.createOrder(request));

        // then
        assertTrue(ex.getMessage().contains("Cart with Id="));
        verify(cartRepository).findById(cartId);
        verifyNoMoreInteractions(cartRepository);
        verifyNoInteractions(orderRepository, orderMapper);
    }

    @Test
    void shouldThrowStoreExceptionWhenCreateOrderWithMissingBuyer() {
        // given
        OrderRequest request = new OrderRequest();
        request.setCartId(cartId);
        Cart cart = new Cart();
        cart.setBuyer(null);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        // when
        StoreException ex = assertThrows(StoreException.class, () -> orderService.createOrder(request));

        // then
        assertTrue(ex.getMessage().contains("Buyer reference must be set on the cart"));
        verify(cartRepository).findById(cartId);
        verifyNoInteractions(orderRepository, orderMapper);
    }

    @Test
    void shouldReturnOrderResponseWhenGetOrderWithExistingId() {
        // given
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        OrderResponse mapped = new OrderResponse()
                .orderId(orderId)
                .status(io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderStatus.PLACED)
                .orderDate(order.getOrderDate());
        when(orderMapper.toOrderResponse(order)).thenReturn(mapped);

        // when
        OrderResponse response = orderService.getOrder(orderId);

        // then
        assertNotNull(response);
        assertEquals(orderId, response.getOrderId());
        verify(orderRepository).findById(orderId);
        verify(orderMapper).toOrderResponse(order);
    }

    @Test
    void shouldThrowStoreExceptionWhenGetOrderWithMissingId() {
        // given
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // when
        StoreException ex = assertThrows(StoreException.class, () -> orderService.getOrder(orderId));

        // then
        assertTrue(ex.getMessage().contains("Order with Id="));
        verify(orderRepository).findById(orderId);
        verifyNoInteractions(orderMapper);
    }
}
