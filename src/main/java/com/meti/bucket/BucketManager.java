package com.meti.bucket;

import com.meti.util.CollectionUtil;

import java.util.HashSet;
import java.util.Set;
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

    public Set<Bucket<T>> byParameters(Object... parameters) {
        return buckets.stream()
                .filter(tBucket -> {
                    try {
                        return tBucket.containsAllParameters(parameters);
                    } catch (ClassCastException e) {
                        return false;
                    }
                })
                .collect(Collectors.toSet());
    }

    public Bucket<T> byParametersToSingle(Object... parameters){
        return CollectionUtil.toSingle(byParameters(parameters));
    }

    public void add(T test) {
        Set<Bucket<T>> validBuckets = buckets.stream()
                .filter(bucket -> bucket.canAccept(test))
                .collect(Collectors.toSet());

        if(validBuckets.size() == 0 ){
            Bucket<T> allocatedBucket = allocationFunction.apply(test);
            allocatedBucket.handle(test);
            buckets.add(allocatedBucket);
        }
        else{
            validBuckets.forEach(bucket -> bucket.handle(test));
        }
    }
}
