package com.example.tdd;

public class FizzBuzz {
    //if number is divisible by 3, print Fizz
    //if number is divisible by 5, print Buzz
    //if number is divisible by 3 and 5, print FizzBuzz
    //if number is not devisible by 3 or 5, then print the number
    public static String compute(int i) {
        StringBuilder sb = new StringBuilder();
        if (i % 3 == 0) sb.append("Fizz");
        if (i % 5 == 0) sb.append("Buzz");
        if (sb.isEmpty()) sb.append(i);
        return sb.toString();
    }


   /* public static String compute(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) return "Fizz";
        else if (i % 5 == 0) return "Buzz";
        return Integer.toString(i);
    }*/
}
