package com.example.test;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
class TestApplicationTests {
    Example example;
    DemoUtils demoUtils = new DemoUtils();

    @Test
    @DisplayName("Equals and not equals")
    public void EqualsAndNotEqual() {
//        Example example = new Example();
        int expected = 5;
        int add = example.add(2, 3);
        assertEquals(expected, add, "2 plus 3 must be 5");
    }

    @Test
    @DisplayName("Null and not Null")
    public void NullAndNotNull() {
//        Example example = new Example();
        assertNotNull(example.checkIfNotNull("Test"), "Test must not be null");
        assertNull(example.checkIfNotNull(null), "Test must be null");
    }

    @Test
    @Order(3)
    void SameNotSame() {
        String same = "academy";
        assertNotSame(demoUtils.getS1(), demoUtils.getS2(), "S1 must not be same");
        assertSame(same, demoUtils.getS1(), "They must be same");
    }

    @Test
    @DisplayName("True Or False")
    @Order(1)
    void TrueOrFalse() {
        assertTrue(demoUtils.isGreater(18, 2), "this should return true");
        assertFalse(demoUtils.isGreater(1, 2), "this should return false");
    }

    @Test
    @DisplayName("Iterable equals")
    void TestIterableEquals() {
        List<String> list = List.of("c", "h", "a", "i", "m", "a", "e");
        assertIterableEquals(list, demoUtils.getList(), "expected list should be equal");
    }

    @Test
    @DisplayName("Array equals")
    void testArrayEquals() {
        String[] array = {"c", "h", "a", "i", "m", "a", "e"};
        assertArrayEquals(array, demoUtils.getList().toArray(), "expected array should be equal");
    }

    @Test
    @DisplayName("Lines match")
    void testLinesMatch() {
        List<String> list = List.of("c", "h", "a", "i", "m", "a", "e");
        assertLinesMatch(list, demoUtils.getList(), "lines should match");
    }

    @Test
    @DisplayName("throw and Doesnt throw exception")
    void throwOAndDoesntThrow() {
        assertThrows(Exception.class, () -> {
            demoUtils.throwAndDoesnotThrow(-5);
        }, "it should throw the Exception");
        assertDoesNotThrow(() -> {
            demoUtils.throwAndDoesnotThrow(5);
        }, "it should not throw the Exception");
    }

    @Test
    void testTimeOut() {
        assertTimeoutPreemptively(Duration.ofMillis(3000), () -> {
            demoUtils.checkTimeout();
        }, "method should execute in 3 seconds");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each Test");
        example = new Example();
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each Test");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All Test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All Test");
    }
}
