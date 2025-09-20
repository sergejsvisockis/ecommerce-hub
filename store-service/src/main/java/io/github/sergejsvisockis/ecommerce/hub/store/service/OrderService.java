package io.github.sergejsvisockis.ecommerce.hub.store.service;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Cart;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Order;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.OrderStatus;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.CartRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.OrderRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.exception.StoreException;
import io.github.sergejsvisockis.ecommerce.hub.store.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(CartRepository cartRepository, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        UUID cartId = orderRequest.getCartId();

        if (cartId == null) {
            throw new StoreException("CartId is required");
        }

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new StoreException(
                        "Cart with Id=" + cartId + " not found."));

        if (cart.getBuyer() == null) {
            throw new StoreException("Buyer reference must be set on the cart");
        }

        Order order = createOrder(cart);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new StoreException(
                        "Order with Id=" + orderId + " not found."));

        return orderMapper.toOrderResponse(order);
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        order.setCart(cart);

        return order;
    }

}
