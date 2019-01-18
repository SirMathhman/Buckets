package com.meti.test;

import com.meti.Bucket;
import com.meti.TypePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class BucketTest {
    @Test
    void appliesNotApplicable() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(Foo.class));
        assertFalse(bucket.canAccept("test"));
    }

    @Test
    void appliesApplicable() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(Foo.class));
        assertTrue(bucket.canAccept(new Foo()));
    }

    @Test
    void appliesSubclass() {
        Bucket<Object> bucket = new Bucket<>(new TypePredicate<>(Foo.class));
        assertTrue(bucket.canAccept(new Bar()));
    }
}
