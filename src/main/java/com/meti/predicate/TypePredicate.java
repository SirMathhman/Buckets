package com.meti.predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public class TypePredicate<T> extends ParameterizedPredicate<Class<T>, Class<?>> {
    private final boolean subClass;

    public TypePredicate(Class<T> testClass, boolean useSubClass) {
        super(testClass);
        this.subClass = useSubClass;
    }

    @Override
    public boolean test(Class<?> aClass) {
        if (subClass) {
            return parameters.get(0).isAssignableFrom(aClass);
        }
        else{
            return parameters.get(0).equals(aClass);
        }
    }
}
