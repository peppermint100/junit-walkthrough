package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Slf4j
public class EnvironmentTests {
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

    @Test
    @EnabledOnJre({ JRE.JAVA_11 })
    void onlyForJava11() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    @EnabledOnJre({ JRE.JAVA_17 })
    void onlyForJava17() {
        Assertions.assertEquals(1, 2);
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_11)
    void notFromJava9to11() {
        Assertions.assertEquals(1, 1);
    }
}
