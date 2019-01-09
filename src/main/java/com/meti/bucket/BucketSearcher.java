package com.meti.bucket;

import com.meti.Parameterized;

import java.util.function.Predicate;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public class BucketSearcher<P> {
    private final Parameterized<P> parameters;

    public BucketSearcher(Parameterized<P> parameters) {
        this.parameters = parameters;
    }

    public void search(BucketManager<?, ? extends Bucket<?, ?>> manager){
        manager.buckets().stream().filter(new Predicate<Bucket<?, ?>>() {
            @Override
            public boolean test(Bucket<?, ?> bucket) {

            }
        })
    }
}
