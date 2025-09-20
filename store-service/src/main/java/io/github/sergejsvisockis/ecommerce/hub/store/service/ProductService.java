package io.github.sergejsvisockis.ecommerce.hub.store.service;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.ProductRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Product;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.ProductRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toApiProducts(products);
    }

    public io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toApiProduct(savedProduct);
    }
}
