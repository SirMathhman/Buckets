package com.meti.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> T toSingle(Collection<T> collection) {
        if (collection.size() != 1) {
            throw new IllegalArgumentException("Cannot make " + collection + " single");
        }

        return new ArrayList<>(collection).get(0);
    }
}
