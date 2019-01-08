package com.meti.bucket;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public abstract class PredicateBucket<T, C extends Collection<T>> extends AbstractBucket<T, C> {
    public final Predicate<T> filter;

    protected PredicateBucket(C collection, Predicate<T> filter) {
        super(collection);
        this.filter = filter;
    }

    @Override
    public boolean canUse(T element) {
        return filter.test(element);
    }
}
