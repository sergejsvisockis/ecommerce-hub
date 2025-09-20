package io.github.sergejsvisockis.ecommerce.hub.store.service;

import io.github.sergejsvisockis.ecommerce.hub.store.api.model.ProductRequest;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.entity.Product;
import io.github.sergejsvisockis.ecommerce.hub.store.domain.repository.ProductRepository;
import io.github.sergejsvisockis.ecommerce.hub.store.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnMappedProductsWhenGetAllProducts() {
        // given
        Product product1 = new Product();
        product1.setName("Phone");
        product1.setIsoCode("USD");

        Product product2 = new Product();
        product2.setName("Laptop");
        product2.setIsoCode("USD");

        List<Product> domainProducts = List.of(product1, product2);
        when(productRepository.findAll()).thenReturn(domainProducts);

        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product apiProd1 =
                new io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product()
                        .productId(UUID.randomUUID())
                        .name("Phone");
        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product apiProd2 =
                new io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product()
                        .productId(UUID.randomUUID())
                        .name("Laptop");
        List<io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product> apiProducts = List.of(apiProd1, apiProd2);
        when(productMapper.toApiProducts(domainProducts)).thenReturn(apiProducts);

        // when
        List<io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product> result = productService.getAllProducts();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Phone", result.get(0).getName());
        verify(productRepository).findAll();
        verify(productMapper).toApiProducts(domainProducts);
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {
        // given
        when(productRepository.findAll()).thenReturn(List.of());
        when(productMapper.toApiProducts(List.of())).thenReturn(List.of());

        // when
        List<io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product> result = productService.getAllProducts();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository).findAll();
        verify(productMapper).toApiProducts(List.of());
    }

    @Test
    void shouldReturnMappedProductWhenCreateProductWithValidRequest() {
        // given
        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Price apiPrice =
                new io.github.sergejsvisockis.ecommerce.hub.store.api.model.Price()
                        .amount("299.99");
        ProductRequest request = new ProductRequest()
                .name("Tablet")
                .price(apiPrice)
                .isoCode("USD");

        Product toSave = new Product();
        toSave.setName("Tablet");
        toSave.setIsoCode("USD");

        Product saved = new Product();
        saved.setName("Tablet");
        saved.setIsoCode("USD");

        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product mapped =
                new io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product()
                        .productId(UUID.randomUUID())
                        .name("Tablet");

        when(productMapper.toProduct(request)).thenReturn(toSave);
        when(productRepository.save(toSave)).thenReturn(saved);
        when(productMapper.toApiProduct(saved)).thenReturn(mapped);

        // when
        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product result = productService.createProduct(request);

        // then
        assertNotNull(result);
        assertEquals("Tablet", result.getName());
        verify(productMapper).toProduct(request);
        verify(productRepository).save(toSave);
        verify(productMapper).toApiProduct(saved);
    }

    @Test
    void shouldPropagateRepositorySaveCallWhenCreateProduct() {
        // given
        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Price apiPrice =
                new io.github.sergejsvisockis.ecommerce.hub.store.api.model.Price()
                        .amount("199.90");
        ProductRequest request = new ProductRequest()
                .name("Monitor")
                .price(apiPrice)
                .isoCode("USD");

        Product toSave = new Product();
        toSave.setName("Monitor");
        toSave.setIsoCode("USD");

        Product saved = new Product();
        saved.setName("Monitor");
        saved.setIsoCode("USD");

        io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product mapped =
                new io.github.sergejsvisockis.ecommerce.hub.store.api.model.Product()
                        .productId(UUID.randomUUID())
                        .name("Monitor");

        when(productMapper.toProduct(request)).thenReturn(toSave);
        when(productRepository.save(toSave)).thenReturn(saved);
        when(productMapper.toApiProduct(saved)).thenReturn(mapped);

        // when
        productService.createProduct(request);

        // then
        verify(productRepository, times(1)).save(toSave);
    }
}
