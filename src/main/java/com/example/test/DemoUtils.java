package com.example.test;

import java.util.List;

public class DemoUtils {
    private String s1 = "academy";
    private String s2 = "academy duplicate";
    private List<String> list = List.of("c", "h", "a", "i", "m", "a", "e");

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public Boolean isGreater(int s1, int s2) {
        return s1 > s2;
    }

    public List<String> getList() {
        return list;
    }

    public void checkTimeout() throws InterruptedException {
        System.out.println("I'm going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }

    public int throwAndDoesnotThrow(int a) throws Exception {
        if (a < 0) throw new Exception("a is less than 0");
        return a;
    }
}
