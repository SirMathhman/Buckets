package com.meti.predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public class TypePredicate<T> extends ParameterizedPredicate<Class<T>, Class<T>> {
    private final boolean subClass;

    public TypePredicate(Class<T> testClass, boolean useSubClass) {
        super(testClass);
        this.subClass = useSubClass;
    }

    @Override
    public boolean test(Class<T> aClass) {
        if (subClass) {
            return aClass.isAssignableFrom(parameters.get(0));
        }
        else{
            return aClass.equals(parameters.get(0));
        }
    }
}
