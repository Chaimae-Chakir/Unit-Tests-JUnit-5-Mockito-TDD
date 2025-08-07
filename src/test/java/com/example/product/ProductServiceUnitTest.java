package com.example.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//@SpringBootTest(classes = TestApplication.class)
@SpringBootTest(classes = ProductServiceUnitTest.class)
public class ProductServiceUnitTest {
    @Mock
//    @MockitoBean
    private ProductRepository productRepository;
    @InjectMocks
//    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(5400.0);
    }

    @Test
    @DisplayName("Return product By Id")
    void shouldReturnProductById() {
        when(productRepository.findById(1L))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.of(product));
        assertThrows(RuntimeException.class, () -> productService.getProductById(1L));
        Product result = productService.getProductById(1L);
        assertEquals(result, product);
        verify(productRepository, times(2)).findById(1L);
    }
}
