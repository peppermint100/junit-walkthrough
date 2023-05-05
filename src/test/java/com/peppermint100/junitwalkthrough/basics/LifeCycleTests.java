package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class LifeCycleTests {
    @BeforeAll
    static void initAll() {
        log.info("Before All");
    }

    @BeforeEach
    void init() {
        log.info("Before Each");
    }

    @Test
    void succeedingTest() {
        log.info("Test Succeed");
    }

    @Test
    void failingTest() {
        fail("Test Failed");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        log.info("Test Skipped");
    }

    @Test
    void abortedTest() {
        Assumptions.assumeTrue("abc".contains("Z"));
        fail("Test Aborted");
    }

    @AfterEach
    void tearDown() {
        log.info("After Each");
    }

    @AfterAll
    static void tearDownAll() {
        log.info("After All");
    }
}
