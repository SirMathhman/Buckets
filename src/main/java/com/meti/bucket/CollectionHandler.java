package com.meti.bucket;

import com.meti.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/22/2019
 */
public class CollectionHandler<T, C extends Collection<T>> implements BucketHandler<T> {
    private final C collection;

    public CollectionHandler(C collection) {
        this.collection = collection;
    }

    @Override
    public void accept(T object) {
        collection.add(object);
    }

    public static <R> CollectionHandler<R, ArrayList<R>> empty() {
        return new CollectionHandler<>(new ArrayList<>());
    }

    public C getElements() {
        return collection;
    }

    public T toSingle() {
        return CollectionUtil.toSingle(collection);
    }
}
