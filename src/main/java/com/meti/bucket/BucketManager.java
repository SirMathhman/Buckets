package com.meti.bucket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
class BucketManager<T, B extends Bucket<T, ?>> {
    private final Function<T, B> bucketAllocator;
    private final Set<B> buckets;

    public BucketManager() {
        this(Stream.empty());
    }

    public BucketManager(Stream<B> bucketStream) {
        this(null, bucketStream);
    }

    private BucketManager(Function<T, B> bucketAllocator, Stream<B> bucketStream) {
        this.bucketAllocator = bucketAllocator;
        this.buckets = bucketStream.collect(Collectors.toSet());
    }

    public BucketManager(Function<T, B> bucketAllocator) {
        this(bucketAllocator, Stream.empty());
    }

    public Map<B, Stream<T>> compoundStream() {
        Map<B, Stream<T>> map = new HashMap<>();
        buckets.forEach(bucket -> map.put(bucket, bucket.getCollection().stream()));
        return map;
    }

    public int bucketCount() {
        return buckets.size();
    }

    public int elementCount() {
        return buckets.stream()
                .mapToInt(bucket -> bucket.getCollection().size())
                .sum();
    }

    public Stream<T> elementStream() {
        return buckets.stream().flatMap((Function<B, Stream<T>>) b -> b.getCollection().stream());
    }

    public void clear() {
        buckets.clear();
    }

    public Map<T, Set<B>> removeAll(Stream<T> elementStream) {
        Map<T, Set<B>> filledBucketMap = new HashMap<>();
        elementStream.forEach(element -> {
            Set<B> filledBuckets = remove(element);
            if (filledBuckets.size() != 0) {
                filledBucketMap.put(element, filledBuckets);
            }
        });
        return filledBucketMap;
    }

    public Set<B> remove(T element) {
        return buckets.stream()
                .filter(bucket -> bucket.getCollection().contains(element))
                .peek(bucket -> bucket.getCollection().remove(element))
                .collect(Collectors.toSet());
    }

    public Map<T, Set<B>> addAll(Stream<T> elementStream) {
        Map<T, Set<B>> filledBucketMap = new HashMap<>();
        elementStream.forEach(element -> {
            Set<B> filledBuckets = add(element);
            if (filledBuckets.size() != 0) {
                filledBucketMap.put(element, filledBuckets);
            }
        });
        return filledBucketMap;
    }

    public Set<B> add(T element) {
        Set<B> filledBuckets = buckets.stream()
                .filter(tcBucket -> tcBucket.canUse(element))
                .peek(tcBucket -> tcBucket.getCollection().add(element))
                .collect(Collectors.toSet());
        if (filledBuckets.isEmpty() && bucketAllocator != null) {
            buckets.add(bucketAllocator.apply(element));
            return add(element);
        } else {
            return filledBuckets;
        }
    }

    public Set<B> buckets() {
        return buckets;
    }
}
