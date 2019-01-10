package com.meti.bucket;

import java.util.Collection;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public abstract class AbstractBucket<T, C extends Collection<T>> implements Bucket<T, C> {
    public final C collection;

    AbstractBucket(C collection) {
        this.collection = collection;
    }

    @Override
    public C getCollection() {
        return collection;
    }
}
