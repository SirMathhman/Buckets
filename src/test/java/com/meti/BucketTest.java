package com.meti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class BucketTest {

    @Test
    void checkAcceptTrue() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertDoesNotThrow(() -> bucket.checkAccept("test"));
    }

    @Test
    void checkAcceptFalse() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertThrows(IllegalArgumentException.class, () -> bucket.checkAccept(0));
    }

    @Test
    void canAcceptTrue() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertTrue(bucket.canAccept("test"));
    }

    @Test
    void canAcceptFalse() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertFalse(bucket.canAccept(new ArrayList<>()));
    }
}