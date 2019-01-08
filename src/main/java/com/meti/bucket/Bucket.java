package com.meti.bucket;

import java.util.Collection;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public interface Bucket<T, C extends Collection<T>> {
    boolean canUse(T element);
    C getCollection();
}
