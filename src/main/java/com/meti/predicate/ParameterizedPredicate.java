package com.meti.predicate;

import com.meti.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public ParameterizedPredicate(Stream<P> parameters) {
        this.parameters.addAll(parameters.collect(Collectors.toSet()));
    }

    @Override
    public Collection<P> getParameters() {
        return parameters;
    }
}
