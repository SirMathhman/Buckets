package com.meti.bucket;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class TypeFunction<T> implements Consumer<Object>, Predicate<T> {
    private final Class<T> tClass;

    public TypeFunction(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void accept(Object o) {

    }

    @Override
    public boolean test(T t) {
        return false;
    }
}
