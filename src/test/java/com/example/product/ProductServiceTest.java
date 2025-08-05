package com.example.product;

import com.example.test.TestApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TestApplication.class)
public class ProductServiceTest {
    @Mock
//    @MockitoBean
    private ProductRepository productRepository;
    @InjectMocks
//    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("Return product By Id")
    void shouldReturnProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(5400.0);
        when(productRepository.findById(1L))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.of(product));
        assertThrows(RuntimeException.class, () -> productService.getProductById(1L));
        Product result = productService.getProductById(1L);
        assertEquals(result, product);
        verify(productRepository, times(2)).findById(1L);
    }
}
