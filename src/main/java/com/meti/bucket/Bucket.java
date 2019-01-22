package com.meti.bucket;

import com.meti.predicate.Parameterized;

import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class Bucket<T> {
    final Predicate<T> predicate;
    final BucketHandler<T> handler;

    public Bucket(Predicate<T> predicate) {
        this(predicate, CollectionHandler.empty());
    }

    public Bucket(Predicate<T> predicate, BucketHandler<T> handler) {
        this.predicate = predicate;
        this.handler = handler;
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

        handler.handle(object);
    }

    public void handleAll(T... objects){
        for (T object : objects) {
            handle(object);
        }
    }
}
