package com.meti.bucket;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/18/2019
 */
public class BucketManager<T> {
    final Function<T, Bucket<T>> allocationFunction;
    final Set<Bucket<T>> buckets = new HashSet<>();

    public BucketManager(Function<T, Bucket<T>> allocationFunction) {
        this.allocationFunction = allocationFunction;
    }

    public void add(T test) {
        Set<Bucket<T>> validBuckets =buckets.stream()
                .filter(bucket -> bucket.canAccept(test))
                .collect(Collectors.toSet());

        if(validBuckets.size() == 0 ){
            Bucket<T> allocatedBucket = allocationFunction.apply(test);
            allocatedBucket.add(test);
            buckets.add(allocatedBucket);
        }
        else{
            validBuckets.forEach(bucket -> bucket.add(test));
        }
    }
}
