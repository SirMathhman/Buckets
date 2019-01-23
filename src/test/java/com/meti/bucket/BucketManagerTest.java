package com.meti.bucket;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class BucketManagerTest {
    @Test
    void test(){
        Function<Object, Bucket<Object>> function = new Function<Object, Bucket<Object>>() {
            @Override
            public Bucket<Object> apply(Object o) {
                return null;
            }
        };
        BucketManager<Object> manager = new BucketManager<>(function);
    }
}
