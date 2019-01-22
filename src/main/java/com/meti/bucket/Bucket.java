package com.meti.bucket;

import com.meti.predicate.Parameterized;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class Bucket<T, H extends BucketHandler<T>> {
    final Predicate<T> predicate;
    final H handler;

    @SuppressWarnings("WeakerAccess")
    public Bucket(Predicate<T> predicate, H handler) {
        this.predicate = predicate;
        this.handler = handler;
    }

    public static <T> Bucket<T, CollectionHandler<T, Set<T>>> createBucket(Predicate<T> predicate) {
        return new Bucket<>(predicate, new CollectionHandler<>(new HashSet<>()));
    }

    public boolean containsAllParameters(Object... parameters) {
        for (Object parameter : parameters) {
            if (!containsParameter(parameter)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsParameter(Object parameter) {
        Parameterized parameterized = checkParameterized();
        return parameterized.getParameters().contains(parameter);
    }

    private Parameterized checkParameterized() {
        return ((Parameterized) predicate);
    }

    public void checkAccept(T object) {
        if (!canAccept(object)) {
            throw new IllegalArgumentException("Cannot accept " + object);
        }
    }

    public boolean canAccept(T object) {
        return predicate.test(object);
    }

    public void handle(T object) {
        checkAccept(object);

        handler.accept(object);
    }

    @SafeVarargs
    public final void handleAll(T... objects){
        for (T object : objects) {
            handle(object);
        }
    }
}
