package com.example.product;

import com.example.TestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class ProductServiceIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductService productService;

    @Before
    public void setup() {
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("INSERT INTO product (name, price) VALUES ('TestProduct', 99.99)");
    }

    @Test
    @DisplayName("Should retrieve product by ID from database")
    public void testGetProductById() {
        Long id = jdbcTemplate.queryForObject("SELECT id FROM product WHERE name = 'TestProduct'", Long.class);
        Product product = productService.getProductById(id);
        assertNotNull(product);
        assertEquals("TestProduct", product.getName());
        assertEquals(99.99, product.getPrice(), 0.01);
    }

    @Test
    @DisplayName("Should check if product exists by ID")
    public void testCheckProductIfNull() {
        Long id = jdbcTemplate.queryForObject("SELECT id FROM product WHERE name = 'TestProduct'", Long.class);
        assertTrue(productService.checkProductIfNull(id));
        assertFalse(productService.checkProductIfNull(9999L));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("Should throw exception when product not found by ID")
    public void testGetProductByIdNotFound() {
        productService.getProductById(9999L);
    }
}
