package com.meti.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.meti.util.CollectionUtil.toSingle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class CollectionUtilTest {

    @Test
    void toSingleOne() {
        String result = toSingle(Collections.singletonList("test"));
        assertEquals("test", result);
    }

    @Test
    void toSingleZero() {
        assertThrows(IllegalArgumentException.class, () -> toSingle(new ArrayList<>()));
    }

    @Test
    void toSingleMultiple() {
        assertThrows(IllegalArgumentException.class, () -> toSingle(Arrays.asList("test0", "test1")));
    }
}