package com.meti.test.bucket;

import com.meti.bucket.PredicateBucket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PredicateBucketTest {

    @Test
    void checkTrue() {
        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s -> s.equals("test"));
        bucket.getCollection().add("test");
        bucket.getCollection().add("test");

        assertDoesNotThrow(bucket::check);
    }

    @Test
    void checkFalse() {
        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s -> s.equals("test"));
        bucket.getCollection().add("test0");
        bucket.getCollection().add("test1");

        assertThrows(IllegalStateException.class, bucket::check);
    }

    @Test
    void checkBoth() {
        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s -> s.equals("test"));
        bucket.getCollection().add("test");
        bucket.getCollection().add("test0");

        assertThrows(IllegalStateException.class, bucket::check);
    }

    @Test
    void canUseTrue() {
        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s -> s.equals("test"));
        assertTrue(bucket.canUse("test"));
    }

    @Test
    void canUseFalse() {
        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s -> s.equals("test"));
        assertFalse(bucket.canUse("test0"));
    }
}