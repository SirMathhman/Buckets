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
        Function<Object, Bucket<Object, CollectionHandler<Object, Set<Object>>>> allocationFunction = o -> Bucket.createBucket(new TypePredicate<>(o.getClass()));
        BucketManager<Object, CollectionHandler<Object, Set<Object>>> bucketManager = new BucketManager<>(allocationFunction);
        assertEquals(allocationFunction, bucketManager.allocationFunction);
    }

    @Test
    void addNoPrevious() {
        String testItem = "test0";

        BucketManager<Object, CollectionHandler<Object, Set<Object>>> bucketManager = new BucketManager<>(o -> Bucket.createBucket(new TypePredicate<>(o.getClass())));
        bucketManager.add(testItem);

        Set<Bucket<Object, CollectionHandler<Object, Set<Object>>>> buckets = bucketManager.buckets;
        assertEquals(1, buckets.size());

        Bucket<Object, ?> bucket = CollectionUtil.toSingle(buckets);
        Predicate<Object> predicate = bucket.predicate;

        assertTrue(predicate.test("test1"));
        assertFalse(predicate.test(Integer.class));

        BucketHandler<Object> handler = bucket.handler;
        assertTrue(CollectionHandler.class.isAssignableFrom(handler.getClass()));
        Set<Object> elements = ((CollectionHandler<Object, Set<Object>>) handler).getElements();
        assertEquals(1, elements.size());
        assertTrue(elements.contains(testItem));
    }

    @Test
    void addWithPrevious() {
        String testItem = "test0";

        BucketManager<Object, CollectionHandler<Object, Set<Object>>> bucketManager = new BucketManager<>(o -> Bucket.createBucket(new TypePredicate<>(o.getClass())));
        bucketManager.add("test1");
        bucketManager.add(testItem);

        Set<Bucket<Object, CollectionHandler<Object, Set<Object>>>> buckets = bucketManager.buckets;
        assertEquals(1, buckets.size());

        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = CollectionUtil.toSingle(buckets);
        Predicate<Object> predicate = bucket.predicate;

        assertTrue(predicate.test("test2"));
        assertFalse(predicate.test(Integer.class));

        Set<Object> elements = bucket.handler.getElements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(testItem));
    }

    @Test
    void byParameters() {
        BucketManager<Object, CollectionHandler<Object, Set<Object>>> bucketManager = new BucketManager<>(o -> Bucket.createBucket(new TypePredicate<>(o.getClass())));
        bucketManager.add(10);
        bucketManager.add("test0");
        bucketManager.add(new ArrayList<>());

        Set<Bucket<Object, CollectionHandler<Object, Set<Object>>>> buckets = bucketManager.byParameters(String.class);
        assertEquals(1, buckets.size());

        Bucket<Object, CollectionHandler<Object, Set<Object>>> bucket = CollectionUtil.toSingle(buckets);
        assertEquals("test0", bucket.handler.toSingle());
    }

    @Test
    void byParametersEmpty() {
        BucketManager<Object, CollectionHandler<Object, Set<Object>>> bucketManager = new BucketManager<>(o -> Bucket.createBucket(o1 -> false));
        bucketManager.buckets.add(Bucket.createBucket(o1 -> false));
        Set<Bucket<Object, CollectionHandler<Object, Set<Object>>>> test = bucketManager.byParameters("test");
        assertTrue(test.isEmpty());
    }

    @Test
    void byParametersToSingle() {
        BucketManager<Object, CollectionHandler<Object, Set<Object>>> bucketManager = new BucketManager<>(o -> Bucket.createBucket(new TypePredicate<>(o.getClass())));
        bucketManager.add(10);
        bucketManager.add("test0");
        bucketManager.add(new ArrayList<>());

        Object token = bucketManager.byParametersToSingle(String.class).handler.toSingle();
        assertEquals("test0", token);
    }
}