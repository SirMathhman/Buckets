package com.meti.test.bucket;

import com.meti.bucket.AbstractBucket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AbstractBucketTest {

    @Test
    void getCollection() {
        List<Object> list = new ArrayList<>();
        AbstractBucket<?, ?> bucket = new AbstractBucket<Object, List<Object>>(list) {
            @Override
            public boolean canUse(Object element) {
                return false;
            }
        };

        Assertions.assertEquals(list, bucket.getCollection());
    }
}