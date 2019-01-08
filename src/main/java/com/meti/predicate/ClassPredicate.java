package com.meti.predicate;

import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public class ClassPredicate<T> implements Predicate<Class<T>> {
    private final Class<T> testClass;
    private final boolean subClass;

    public ClassPredicate(Class<T> testClass, boolean useSubClass) {
        this.testClass = testClass;
        this.subClass = useSubClass;
    }

    @Override
    public boolean test(Class<T> aClass) {
        if (subClass) {
            return aClass.isAssignableFrom(testClass);
        }
        else{
            return aClass.equals(testClass);
        }
    }
}
