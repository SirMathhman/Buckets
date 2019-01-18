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

    public boolean remove(T object) {
        checkAccept(object);

        return elements.remove(object);
    }

    public void checkAccept(T object) {
        if (!canAccept(object)) {
            throw new IllegalArgumentException("Cannot accept " + object);
        }
    }

    public void add(T object) {
        checkAccept(object);

        elements.add(object);
    }

    public boolean canAccept(T object) {
        return objectPredicate.test(object);
    }
}
