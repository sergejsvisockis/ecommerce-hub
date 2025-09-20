package io.github.sergejsvisockis.ecommerce.hub.store.mapper;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.OrderResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);

}
