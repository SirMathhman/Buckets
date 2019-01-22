package com.meti.bucket;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/22/2019
 */
class CollectionHandlerTest {

    @Test
    void handle() {
        ArrayList<Object> objects = new ArrayList<>();
        CollectionHandler<Object, ArrayList<Object>> handler = new CollectionHandler<>(objects);
        handler.accept("test");

        assertEquals(1, objects.size());
        assertTrue(objects.contains("test"));
    }

    @Test
    void empty() {
        CollectionHandler<Object, ArrayList<Object>> empty = CollectionHandler.empty();
        assertEquals(0, empty.getElements().size());
    }

    @Test
    void getElements() {
        CollectionHandler<String, List<String>> handler = new CollectionHandler<>(Arrays.asList("test0", "test1"));
        List<String> elements = handler.getElements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains("test0"));
        assertTrue(elements.contains("test1"));
    }

    @Test
    void toSingle() {
        CollectionHandler<String, Set<String>> handler = new CollectionHandler<>(Collections.singleton("test"));
        assertEquals("test", handler.toSingle());
    }
}