package com.meti.bucket;

import com.meti.predicate.ParameterizedPredicate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BucketSearcher<T, B extends Bucket<T, ?>> {
    public final Map<Collection<?>, B> bucketMap = new HashMap<>();

    public BucketSearcher() {
    }

    public BucketSearcher(BucketManager<T, B> manager){
        index(manager);
    }

    private void index(BucketManager<T, B> bucketManager) {
        for (B bucket : bucketManager.buckets()) {
            if (bucket instanceof PredicateBucket) {
                Predicate filter = ((PredicateBucket) bucket).filter;
                if (filter instanceof ParameterizedPredicate) {
                    ParameterizedPredicate parameterizedPredicate = (ParameterizedPredicate) filter;
                    bucketMap.put(parameterizedPredicate.getParameters(), bucket);
                }
            }
        }
    }

    public Set<B> search(Stream<?> objects) {
        Set<?> objectSet = objects.collect(Collectors.toSet());
        return bucketMap.keySet()
                .stream()
                .filter(collection -> collection.containsAll(objectSet))
                .map(bucketMap::get)
                .collect(Collectors.toSet());
    }

    public void clear(){
        bucketMap.clear();
    }
}
