package com.meti.test.predicate;

import com.meti.predicate.ParameterizedPredicate;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParameterizedPredicateTest {

    @Test
    void oneParameter() {
        ParameterizedPredicate<?, ?> predicate = new ParameterizedPredicate<Object, Object>("test") {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };

        Collection<?> parameters = predicate.getParameters();
        assertEquals(1, parameters.size());
        assertTrue(parameters.contains("test"));
    }

    @Test
    void multiParameters(){
        ParameterizedPredicate<?, ?> predicate = new ParameterizedPredicate<Object, Object>(Stream.of("test0", "test1")) {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };

        Collection<?> parameters = predicate.getParameters();
        assertEquals(2, parameters.size());
        assertTrue(parameters.contains("test0"));
        assertTrue(parameters.contains("test1"));
    }
}