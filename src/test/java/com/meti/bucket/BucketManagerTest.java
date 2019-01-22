package com.meti.bucket;

import com.meti.predicate.TypePredicate;
import com.meti.util.CollectionUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class BucketManagerTest {
    @Test
    void construct() {
        Function<Object, Bucket<Object>> allocationFunction = o -> new Bucket<>(new TypePredicate<>(o.getClass()));
        BucketManager<Object> bucketManager = new BucketManager<>(allocationFunction);
        assertEquals(allocationFunction, bucketManager.allocationFunction);
    }

    @Test
    void addNoPrevious() {
        String testItem = "test0";

        BucketManager<Object> bucketManager = new BucketManager<>(o -> new Bucket<>(new TypePredicate<>(o.getClass())));
        bucketManager.add(testItem);

        Set<Bucket<Object>> buckets = bucketManager.buckets;
        assertEquals(1, buckets.size());

        Bucket<Object> bucket = CollectionUtil.toSingle(buckets);
        Predicate<Object> predicate = bucket.predicate;

        assertTrue(predicate.test("test1"));
        assertFalse(predicate.test(Integer.class));

        Set<Object> elements = bucket.elements;
        assertEquals(1, elements.size());
        assertTrue(elements.contains(testItem));
    }

    @Test
    void addWithPrevious() {
        String testItem = "test0";

        BucketManager<Object> bucketManager = new BucketManager<>(o -> new Bucket<>(new TypePredicate<>(o.getClass())));
        bucketManager.add("test1");
        bucketManager.add(testItem);

        Set<Bucket<Object>> buckets = bucketManager.buckets;
        assertEquals(1, buckets.size());

        Bucket<Object> bucket = CollectionUtil.toSingle(buckets);
        Predicate<Object> predicate = bucket.predicate;

        assertTrue(predicate.test("test2"));
        assertFalse(predicate.test(Integer.class));

        Set<Object> elements = bucket.elements;
        assertEquals(2, elements.size());
        assertTrue(elements.contains(testItem));
    }

    @Test
    void byParameters() {
        BucketManager<Object> bucketManager = new BucketManager<>(o -> new Bucket<>(new TypePredicate<>(o.getClass())));
        bucketManager.add(10);
        bucketManager.add("test0");
        bucketManager.add(new ArrayList<>());

        Set<Bucket<Object>> buckets = bucketManager.byParameters(String.class);
        assertEquals(1, buckets.size());

        Bucket<Object> bucket = CollectionUtil.toSingle(buckets);
        assertEquals("test0", bucket.toSingle());
    }

    @Test
    void byParametersEmpty() {
        BucketManager<Object> bucketManager = new BucketManager<>(o -> new Bucket<>(o1 -> false));
        bucketManager.buckets.add(new Bucket<>(o1 -> false));
        Set<Bucket<Object>> test = bucketManager.byParameters("test");
        assertTrue(test.isEmpty());
    }

    @Test
    void byParametersToSingle() {
        BucketManager<Object> bucketManager = new BucketManager<>(o -> new Bucket<>(new TypePredicate<>(o.getClass())));
        bucketManager.add(10);
        bucketManager.add("test0");
        bucketManager.add(new ArrayList<>());

        Object token = bucketManager.byParametersToSingle(String.class).toSingle();
        assertEquals("test0", token);
    }
}