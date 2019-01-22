package com.meti.bucket;

import com.meti.predicate.Parameterized;
import com.meti.util.CollectionUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class Bucket<T> {
    final Predicate<T> predicate;
    final Set<T> elements = new HashSet<>();

    public Bucket(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public Set<T> getElements() {
        return Collections.unmodifiableSet(elements);
    }

    public T toSingle() {
        return CollectionUtil.toSingle(elements);
    }

    public boolean containsAll(Object... parameters) {
        for (Object parameter : parameters) {
            if (!contains(parameter)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Object parameter) {
        Parameterized parameterized = checkParameterized();
        return parameterized.getParameters().contains(parameter);
    }

    private Parameterized checkParameterized() {
        return ((Parameterized) predicate);
    }

    public void clear() {
        elements.clear();
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

    public boolean canAccept(T object) {
        return predicate.test(object);
    }

    public void add(T object) {
        checkAccept(object);

        elements.add(object);
    }

    public void addAll(T... objects){
        for (T object : objects) {
            add(object);
        }
    }

    public int size() {
        return elements.size();
    }
}
