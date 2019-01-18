package com.meti.bucket;

import java.util.function.Function;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class BucketManager<T> {
    final Function<T, Class<?>> allocationFunction;

    public BucketManager(Function<T, Class<?>> allocationFunction) {
        this.allocationFunction = allocationFunction;
    }
}
