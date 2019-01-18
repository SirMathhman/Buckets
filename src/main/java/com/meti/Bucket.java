package com.meti;

import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class Bucket<T> {
    private final Predicate<T> objectPredicate;

    public Bucket(Predicate<T> objectPredicate) {
        this.objectPredicate = objectPredicate;
    }

    public boolean canAccept(T test) {
        return objectPredicate.test(test);
    }
}
