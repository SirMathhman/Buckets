package com.meti.bucket;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class Bucket<T> {
    private final Predicate<T> objectPredicate;
    final Set<T> elements = new HashSet<>();

    public Bucket(Predicate<T> objectPredicate) {
        this.objectPredicate = objectPredicate;
    }

    public boolean canAccept(T test) {
        return objectPredicate.test(test);
    }

    public void checkAccept(T test) {
        if (!canAccept(test)) {
            throw new IllegalArgumentException("Cannot accept " + test);
        }
    }

    public void add(T object) {
        checkAccept(object);

        elements.add(object);
    }
}
