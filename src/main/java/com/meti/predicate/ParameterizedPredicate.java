package com.meti.predicate;

import com.meti.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public abstract class ParameterizedPredicate<P, T> implements Parameterized<P>, Predicate<T> {
    protected final List<P> parameters = new ArrayList<>();

    public ParameterizedPredicate(P parameter) {
        this.parameters.add(parameter);
    }

    public ParameterizedPredicate(P[] parameters) {
        this(Arrays.asList(parameters));
    }

    public ParameterizedPredicate(Collection<P> parameters) {
        this.parameters.addAll(parameters);
    }

    @Override
    public Collection<P> getParameters() {
        return parameters;
    }
}
