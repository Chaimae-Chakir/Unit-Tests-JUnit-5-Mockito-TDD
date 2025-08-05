package com.example.product;

import com.example.test.TestApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TestApplication.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Return product By Id")
    void shouldReturnProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(5400.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(1L);
        assertEquals(result, product);
        verify(productRepository, times(1)).findById(1L);
    }
}
