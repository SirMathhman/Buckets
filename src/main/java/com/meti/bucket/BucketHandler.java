package com.meti.bucket;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/22/2019
 */
public interface BucketHandler<T> {
    void handle(T object);
}
