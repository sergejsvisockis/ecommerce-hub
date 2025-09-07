package io.github.sergejsvisockis.ecommerce.hub.store.api;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiImpl implements ProductsApi {

    @Override
    public ResponseEntity<ProductResponse> getAllProducts() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
