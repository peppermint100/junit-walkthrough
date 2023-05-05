package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Slf4j
public class OSTests {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(OS.MAC)
    @interface TestOnMac {
    }

    @Test
    @EnabledOnOs( { OS.MAC })
    void onlyOnMac() {
        log.info("Tests for mac");
        Assertions.assertEquals(1, 1);
    }

    @Test
    @EnabledOnOs( { OS.WINDOWS })
    void onlyOnWindows() {
        log.info("Tests for windows");
        Assertions.assertEquals(1, 2);
    }

    @TestOnMac
    void onlyOnMacWithCustomAnnotation() {
        log.info("Test for mac with custom annotation");
        Assertions.assertEquals(1, 1);
    }
}
