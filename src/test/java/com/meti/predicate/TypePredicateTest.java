package com.meti.predicate;

import com.meti.predicate.TypePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class TypePredicateTest {
    @Test
    void constructJustClass(){
        TypePredicate<?> predicate = new TypePredicate<>(Object.class);
        assertEquals(Object.class, predicate.clazz);
        assertTrue(predicate.useSubClass);
    }

    @Test
    void constructWithUseSubClass(){
        TypePredicate<?> predicate = new TypePredicate<>(Object.class, true);
        assertTrue(predicate.useSubClass);
    }

    @Test
    void appliesNotEqual(){
        TypePredicate<Number> predicate = new TypePredicate<>(Number.class, false);
        assertFalse(predicate.test(0));
    }

    @Test
    void appliesNotApplicable() {
        TypePredicate<Number> predicate = new TypePredicate<>(Number.class);
        assertFalse(predicate.test("test"));
    }

    @Test
    void appliesApplicable() {
        TypePredicate<Number> predicate = new TypePredicate<>(Number.class);
        assertTrue(predicate.test(new TestNumber()));
    }

    @Test
    void appliesSubclass() {
        TypePredicate<Number> predicate = new TypePredicate<>(Number.class);
        assertTrue(predicate.test(0));
    }

    private static class TestNumber extends Number {
        @Override
        public int intValue() {
            return 0;
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    }
}
