package com.example.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Dont run until Jira #123 is resolved")
    void basicTest() {
        //execute a method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForWindowsOnly() {
        //execute a method and perform asserts
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForWindowsMacOnly() {
        //execute a method and perform asserts
    }

    @Test
//    @EnabledOnJre(JRE.JAVA_9)
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_17)
    void testForJreRange() {
        //execute a method and perform asserts
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "dev")
    void testOnlyOnDevEnvironment() {
        System.out.println("This test runs only when ENV=dev");
    }

    @Test
    @EnabledIfSystemProperty(named = "app.mode", matches = "test")
    void testRunsOnlyInTestMode() {
        System.out.println("This test runs only when system property 'app.mode=test'");
    }
}
