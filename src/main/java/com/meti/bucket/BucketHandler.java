package com.meti.bucket;

import java.util.function.Consumer;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/22/2019
 */
@SuppressWarnings("WeakerAccess")
public interface BucketHandler<T> extends Consumer<T> {
}
