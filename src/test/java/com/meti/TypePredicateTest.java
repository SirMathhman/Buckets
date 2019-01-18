package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
class TypePredicateTest {
    @Test
    void appliesNotEqual(){
        TypePredicate<Foo> predicate = new TypePredicate<>(Foo.class, false);
        assertFalse(predicate.test(new Bar()));
    }

    @Test
    void appliesNotApplicable() {
        TypePredicate<Foo> predicate = new TypePredicate<>(Foo.class);
        assertFalse(predicate.test("test"));
    }

    @Test
    void appliesApplicable() {
        TypePredicate<Foo> predicate = new TypePredicate<>(Foo.class);
        assertTrue(predicate.test(new Foo()));
    }

    @Test
    void appliesSubclass() {
        TypePredicate<Foo> predicate = new TypePredicate<>(Foo.class);
        assertTrue(predicate.test(new Bar()));
    }
}
