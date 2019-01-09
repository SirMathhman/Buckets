package com.meti.predicate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TypePredicateTest {

    @Test
    void useSubClassTrue() {
        TypePredicate<List> predicate = new TypePredicate<>(List.class, true);
        assertTrue(predicate.test(ArrayList.class));
    }

    @Test
    void useSubClassFalse() {
        TypePredicate<List> predicate = new TypePredicate<>(List.class, true);
        assertFalse(predicate.test(HashSet.class));
    }

    @Test
    void withoutSubClassTrue(){
        TypePredicate<String> predicate = new TypePredicate<>(String.class, false);
        assertTrue(predicate.test(String.class));
    }

    @Test
    void withoutSubClassFalse(){
        TypePredicate<List> predicate = new TypePredicate<>(List.class, false);
        assertFalse(predicate.test(ArrayList.class));
    }
}