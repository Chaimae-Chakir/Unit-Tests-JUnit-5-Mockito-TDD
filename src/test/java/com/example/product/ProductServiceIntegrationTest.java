package com.example.product;

import com.example.TestApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

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
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("INSERT INTO product (name, price) VALUES ('TestProduct', 99.99)");
    }

    @Test
    @DisplayName("Should return product with correct name and price when ID exists")
    public void testGetProductById() {
        Long id = jdbcTemplate.queryForObject("SELECT id FROM product WHERE name = 'TestProduct'", Long.class);
        Product product = productService.getProductById(id);
        assertNotNull(product);
        assertEquals("TestProduct", product.getName());
        assertEquals(99.99, product.getPrice(), 0.01);
    }

    @Test
    @DisplayName("Should return true if product exists and false otherwise")
    public void testCheckProductIfNull() {
        Long id = jdbcTemplate.queryForObject("SELECT id FROM product WHERE name = 'TestProduct'", Long.class);
        assertTrue(productService.checkProductIfNull(id));
        assertFalse(productService.checkProductIfNull(9999L));
    }

    @Test
    @DisplayName("Should throw RuntimeException when product is not found")
    public void testGetProductByIdNotFound() {
        assertThrows(RuntimeException.class, () -> productService.getProductById(9999L));
    }
}
