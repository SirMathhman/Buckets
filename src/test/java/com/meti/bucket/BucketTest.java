package com.meti.bucket;

import com.meti.predicate.TypePredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class BucketTest {
    @Test
    void removeValid() {
        String testValue = "test";

        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.elements.add(testValue);

        assertTrue(bucket.remove(testValue));
    }

    @Test
    void removeNotPresent() {
        String testValue = "test";
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertFalse(bucket.remove(testValue));
    }

    @Test
    void removeInvalid() {
        List<?> testValue = new ArrayList<>();

        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertThrows(IllegalArgumentException.class, () -> bucket.remove(testValue));
    }

    @Test
    void addValid() {
        String testValue = "test";

        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.add(testValue);
        assertTrue(bucket.elements.contains(testValue));
    }

    @Test
    void addInvalid() {
        List<?> testValue = new ArrayList<>();

        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        assertThrows(IllegalArgumentException.class, () -> bucket.add(testValue));

        assertFalse(bucket.elements.contains(testValue));
    }

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