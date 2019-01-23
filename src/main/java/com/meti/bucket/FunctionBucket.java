package com.meti.bucket;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionBucket<T> implements Bucket<T> {
    private final Predicate<T> predicate;
    private final Consumer<Object> consumer;

    public FunctionBucket(Predicate<T> predicate, Consumer<Object> consumer) {
        this.predicate = predicate;
        this.consumer = consumer;
    }

    public static <T, H extends Predicate<T> & Consumer<Object>> FunctionBucket<T> fromBoth(H function) {
        return new FunctionBucket<>(function, function);
    }

    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    @Override
    public void accept(Object o) {
        consumer.accept(o);
    }
}
