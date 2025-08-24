package io.github.sergejsvisockis.ecommerce.hub.common.order.dto;

import java.util.UUID;

public record OrderResponse(UUID orderId, OrderState state) {
}
