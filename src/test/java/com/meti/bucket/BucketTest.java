package com.meti.bucket;

import com.meti.predicate.TypePredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class BucketTest {
    @Test
    void construct() {
        Predicate<Object> predicate = new TypePredicate<>(String.class);
        Bucket<Object> bucket = new Bucket<>(predicate);
        assertEquals(predicate, bucket.predicate);
    }

    @Test
    void size() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.elements.addAll(Arrays.asList("test0", "test1"));

        assertEquals(2, bucket.size());
    }

    @Test
    void clear() {
        String testValue = "test";

        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.elements.add(testValue);
        bucket.clear();

        assertEquals(0, bucket.elements.size());
    }

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

    @Test
    void getElements() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.add("test0");
    }

    @Test
    void toSingle() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.elements.add("test");

        Object o = bucket.toSingle();
        assertEquals("test", o);
    }

    @Test
    void containsAll() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        List<String> strings = Arrays.asList("test0", "test1");
        bucket.elements.addAll(strings);

        assertTrue(bucket.containsAll((Object) strings.toArray(new String[0])));
    }

    @Test
    void contains() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.elements.add("test");

        assertTrue(bucket.contains("test"));
    }

    @Test
    void addAll() {
        String testValue0 = "test0";
        String testValue1 = "test1";

        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(String.class));
        bucket.addAll(testValue0, testValue1);

        Set<Object> elements = bucket.elements;
        assertEquals(2, elements.size());
        assertTrue(elements.contains("test0"));
        assertTrue(elements.contains("test1"));
    }
}