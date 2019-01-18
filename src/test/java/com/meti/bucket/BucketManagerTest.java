package com.meti.bucket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class BucketManagerTest {
    @Test
    void construct() {
        Function<Object, Class<?>> allocationFunction = Object::getClass;
        BucketManager<Object> bucketManager = new BucketManager<>(allocationFunction);
        assertEquals(allocationFunction, bucketManager.allocationFunction);
    }
}