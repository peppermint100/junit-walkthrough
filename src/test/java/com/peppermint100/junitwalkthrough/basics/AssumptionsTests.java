package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.*;

@Slf4j
public class AssumptionsTests {

    @Test
    void basicAssumptions() {
        assumeTrue("test".equals("test"));

        assumingThat("firstTest".equals("firstTest"),
                () -> {
                    assumeTrue("secondTest".equals("secondTest"));
                });
    }

    /*
        메소드에 버그가 있어 수정이 필요한 경우 잠시 테스트를 비활성화 해둔다.
        주석처리 하는 것보다 훨씬 neat한 듯
     */
    @Test
    @Disabled("This Test will be skipped")
    void skippedTest() {
        assumeTrue("test".equals("unexpectedTest"));
        log.info("This Test will be skipped");
    }
}
