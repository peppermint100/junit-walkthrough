package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AssertionsTests {

    @Test
    @DisplayName("assertAll 내부의 모든 Assertions이 통과")
    void groupedAssertions() {
        assertAll("person",
                () -> assertEquals("Jane", "Jane"),
                () -> assertEquals("Doe", "Doe")
        );
    }

    @Test
    @DisplayName("assertAll에서 같은 블록의 코드 중 하나가 실패하면 같은 블록 내 다른 테스트는 실행되지 않음")
    void groupedAssertionsFail() {
        assertAll("parent",
            () -> {
                assertAll("child1",
                    () -> {
                        assertEquals("Jane", "Jane");
                        assertEquals("Doe", "Doe2");
                        assertEquals("Kelly", "Kelly2");
                    }
                );
            },
            () -> {
                assertAll("child2",
                    () -> {
                        assertEquals("Jake", "Jake");
                        assertEquals("Oliver", "Oliver2");
                        assertEquals("Bruno", "Bruno");
                    }
                );
            }
        );
    }

    @Test
    @DisplayName("Exception이 정상적으로 throw되는지 테스트")
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
        {
            float i = 1/0;
        });
        log.info(exception.getMessage());
    }

    @Test
    @DisplayName("메소드 실행시간이 Duration보다 오래걸리면 테스트 실패")
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(400);
            return "result";
        });
        assertEquals("result", actualResult);
    }


}