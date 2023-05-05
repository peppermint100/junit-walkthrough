package com.peppermint100.junitwalkthrough.basics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class ParameterizedTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void basicParameterizedTests(int p) {
        Assertions.assertTrue(p < 4);
    }

    @ParameterizedTest
    @NullSource
    void nullParameterizeTests(String p) {
        Assertions.assertThrows(NullPointerException.class, () -> {
            p.equals("Parameter");
        });
    }

    enum Country {
        KOREA, JAPAN, CHINA
    }

    @ParameterizedTest
    @EnumSource(Country.class)
    public void enumSourceTests(Country p) {
        Assertions.assertTrue(p instanceof Country);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = { "JAPAN", "CHINA" })
    void testWithEnumSourceExclude(Country p) {
        Assertions.assertEquals(p, Country.KOREA);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    @DisplayName("Display name of container")
    @ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
    @CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
    void testWithCustomDisplayNames(String fruit, int rank) {
    }
}
