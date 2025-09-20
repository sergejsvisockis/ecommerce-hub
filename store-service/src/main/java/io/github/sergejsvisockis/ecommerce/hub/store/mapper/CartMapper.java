package io.github.sergejsvisockis.ecommerce.hub.store.mapper;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.BuyerReferenceRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Cart;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.CartBuyer;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "createdAt",
            expression = "java(java.time.LocalDateTime.now())")
    Cart toCart(CartRequest cartRequest);

    List<CartItem> toCartItems(List<io.github.sergejsvisockis.ecommerce.hub.store.api.model.CartItem> cartItems);

    CartBuyer toCartItemBuyer(BuyerReferenceRequest buyer);

    CartResponse toCartItemsResponse(Cart cart);
}
