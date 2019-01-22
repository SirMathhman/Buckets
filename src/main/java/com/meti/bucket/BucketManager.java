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
@SuppressWarnings("WeakerAccess")
public class BucketManager<T, H extends BucketHandler<T>> {
    final Function<T, Bucket<T, H>> allocationFunction;
    final Set<Bucket<T, H>> buckets = new HashSet<>();

    public BucketManager(Function<T, Bucket<T, H>> allocationFunction) {
        this.allocationFunction = allocationFunction;
    }

    public Bucket<T, H> byParametersToSingle(Object... parameters) {
        return CollectionUtil.toSingle(byParameters(parameters));
    }

    public Set<Bucket<T, H>> byParameters(Object... parameters) {
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

    public void add(T test) {
        Set<Bucket<T, H>> validBuckets = buckets.stream()
                .filter(bucket -> bucket.canAccept(test))
                .collect(Collectors.toSet());

        if(validBuckets.size() == 0 ){
            Bucket<T, H> allocatedBucket = allocationFunction.apply(test);
            allocatedBucket.handle(test);
            buckets.add(allocatedBucket);
        }
        else{
            validBuckets.forEach(bucket -> bucket.handle(test));
        }
    }
}
