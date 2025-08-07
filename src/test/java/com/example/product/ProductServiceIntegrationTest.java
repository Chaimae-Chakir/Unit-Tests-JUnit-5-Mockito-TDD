package com.example.product;

import com.example.TestApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestApplication.class)
@TestPropertySource("/application.yml")
public class ProductServiceIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setup() {
//        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("INSERT INTO product (name, price) VALUES ('TestProduct', 99.99)");
    }

    @Test
    @DisplayName("Should return true if product exists and false otherwise")
    public void testCheckProductIfNull() {
        assertTrue(productService.checkProductIfNull(1L));
        assertFalse(productService.checkProductIfNull(9999L));
    }

    @Test
    @DisplayName("Should throw RuntimeException when product is not found")
    public void testGetProductByIdNotFound() {
        assertThrows(RuntimeException.class, () -> productService.getProductById(9999L));
    }

    @Sql("/insert-data.sql")
    @Test
    void getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        assertEquals(4, allProducts.size());
    }

}
