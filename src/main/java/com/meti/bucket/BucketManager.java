package com.meti.bucket;

import java.util.function.Function;

public class BucketManager<T> {
    public BucketManager(Function<T, Bucket<T>> function) {
    }
}
