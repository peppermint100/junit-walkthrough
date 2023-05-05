package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@Slf4j
public class OSTests {

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
}
