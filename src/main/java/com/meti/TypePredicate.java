package com.meti;

import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class TypePredicate<T> implements Predicate<Object> {
    private Class<T> clazz;

    public TypePredicate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean test(Object o) {
        return clazz.isAssignableFrom(o.getClass());
    }
}
