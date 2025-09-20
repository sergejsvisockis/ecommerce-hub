package io.github.sergejsvisockis.ecommerce.hub.store.mapper;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.ProductRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest request);

    io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product toApiProduct(Product product);

    List<io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product> toApiProducts(List<Product> product);

}
