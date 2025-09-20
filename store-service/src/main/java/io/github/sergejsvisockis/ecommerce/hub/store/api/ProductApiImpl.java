package io.github.sergejsvisockis.ecommerce.hub.store.api;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.ProductRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.api.model.ProductResponse;
import io.github.sergejsvisockis.ecommerce.hub.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiImpl implements ProductsApi {

    private final ProductService productService;

    public ProductApiImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductResponse> getAllProducts() {
        ProductResponse response = new ProductResponse();
        response.setProducts(productService.getAllProducts());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Product> createProduct(ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }
}
