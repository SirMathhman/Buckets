package com.meti;

import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class TypePredicate<T> implements Predicate<Object> {
    private final boolean useSubClass;
    private final Class<T> clazz;

    public TypePredicate(Class<T> clazz) {
        this(clazz, true);
    }

    public TypePredicate(Class<T> clazz, boolean useSubClass) {
        this.clazz = clazz;
        this.useSubClass = useSubClass;
    }

    @Override
    public boolean test(Object o) {
        if (useSubClass) {
            return clazz.isAssignableFrom(o.getClass());
        } else {
            return clazz.equals(o.getClass());
        }
    }
}
