package com.meti.bucket;

import com.meti.predicate.Parameterized;
import com.meti.predicate.TypePredicate;
import org.junit.jupiter.api.Test;

import java.util.*;
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
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(predicate);
        assertEquals(predicate, bucket.predicate);
    }

    @Test
    void addValid() {
        String testValue = "test";

        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        bucket.handle(testValue);
        assertTrue(bucket.handler.getElements().contains(testValue));
    }

    @Test
    void addInvalid() {
        List<?> testValue = new ArrayList<>();

        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        assertThrows(IllegalArgumentException.class, () -> bucket.handle(testValue));

        assertFalse(bucket.handler.getElements().contains(testValue));
    }

    @Test
    void checkAcceptTrue() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        assertDoesNotThrow(() -> bucket.checkAccept("test"));
    }

    @Test
    void checkAcceptFalse() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        assertThrows(IllegalArgumentException.class, () -> bucket.checkAccept(0));
    }

    @Test
    void canAcceptTrue() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        assertTrue(bucket.canAccept("test"));
    }

    @Test
    void canAcceptFalse() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        assertFalse(bucket.canAccept(new ArrayList<>()));
    }

    @Test
    void getElements() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        bucket.handler.getElements().add("test0");

        Set<Object> elements = bucket.handler.getElements();
        assertEquals(1, elements.size());
        assertTrue(elements.contains("test0"));
    }

    @Test
    void toSingle() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        bucket.handler.getElements().add("test");

        Object o = bucket.handler.toSingle();
        assertEquals("test", o);
    }

    @Test
    void containsAllParametersTrue() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TestPredicate());
        assertTrue(bucket.containsAllParameters(String.class, Integer.class));
    }

    @Test
    void containsAllParametersFalse() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TestPredicate());
        assertFalse(bucket.containsAllParameters(String.class, Void.class));
    }

    @Test
    void containsParameter() {
        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        assertTrue(bucket.containsParameter(String.class));
    }

    @Test
    void addAll() {
        String testValue0 = "test0";
        String testValue1 = "test1";

        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = Bucket.createBucket(new TypePredicate<>(String.class));
        bucket.handleAll(testValue0, testValue1);

        Set<Object> elements = bucket.handler.getElements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains("test0"));
        assertTrue(elements.contains("test1"));
    }

    private class TestPredicate implements Parameterized<Object>, Predicate<Object> {
        @Override
        public boolean test(Object o) {
            return false;
        }

        @Override
        public Set<Object> getParameters() {
            return new HashSet<>(Arrays.asList(String.class, Integer.class));
        }
    }
}