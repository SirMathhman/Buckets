package com.meti.test.bucket;

import com.meti.bucket.BucketManager;
import com.meti.bucket.BucketSearcher;
import com.meti.bucket.PredicateBucket;
import com.meti.predicate.TypePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BucketSearcherTest {
    private PredicateBucket<Class<?>, ArrayList<Class<?>>> bucket;
    private BucketManager<?, ?> manager;

    @BeforeEach
    void beforeEach() {
        bucket = new PredicateBucket<>(new ArrayList<>(), new TypePredicate<>(String.class, false));
        manager = new BucketManager<>(Stream.of(bucket));
    }

    @Test
    void test(){
        BucketSearcher searcher = new BucketSearcher();
        assertEquals(0, searcher.bucketMap.size());
    }

    @Test
    void index() {
        //BucketSearcher.index(BucketManager manager) is automatically called in the constructor
        BucketSearcher<?, ?> bucketSearcher = new BucketSearcher<>(manager);
        Set<Collection<?>> bucketMap = bucketSearcher.bucketMap.keySet();
        assertEquals(1, bucketMap.size());

        Collection<?> objects = new ArrayList<>(bucketMap).get(0);
        assertEquals(1, objects.size());
        assertTrue(objects.contains(String.class));
    }

    @Test
    void search() {
        Set<?> foundBuckets = new BucketSearcher<>(manager).search(Stream.of(String.class));
        assertEquals(1, foundBuckets.size());
        assertEquals(bucket, new ArrayList<>(foundBuckets).get(0));
    }

    @Test
    void clear() {
        BucketSearcher<?, ?> bucketSearcher = new BucketSearcher<>(manager);
        bucketSearcher.clear();

        assertEquals(0, bucketSearcher.bucketMap.size());
    }
}