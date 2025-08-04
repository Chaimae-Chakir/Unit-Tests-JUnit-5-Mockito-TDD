package com.example.tdd;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    //if number is divisible by 3, print Fizz
    //if number is divisible by 5, print Buzz
    //if number is divisible by 3 and 5, print FizzBuzz
    //if number is not devisible by 3 or 5, then print the number
    @Test
    @DisplayName("Divisible by three")
    @Order(1)
    void testForDivisibleByThree() {
        String expected = "Fizz";
        assertEquals(expected, FizzBuzz.compute(3), "should returns Fizz");
    }

    @Test
    @DisplayName("Divisible by five")
    @Order(1)
    void testForDivisibleByFive() {
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.compute(5), "should returns Buzz");
    }

    @Test
    @DisplayName("Divisible by Three And Five")
    void shouldReturnFizzBuzzIfDivisibleByThreeAndFive() {
        assertEquals("FizzBuzz", FizzBuzz.compute(15), "should returns FizzBuzz");
    }

    @Test
    @DisplayName("Not Divisible by Three Or Five")
    void shouldReturnFizzBuzzIfNotDivisibleByThreeOrFive() {
        assertEquals("7", FizzBuzz.compute(7), "should returns 7");
    }
}
