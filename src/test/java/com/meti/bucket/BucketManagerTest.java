package com.meti.bucket;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BucketManagerTest {
    @Test
    void test(){
        assertTrue(new BucketManager<>().buckets().isEmpty());
    }

    @Test
    void compoundStream() {
        PredicateBucket<String, List<String>> bucket0 = new PredicateBucket<>(Arrays.asList("test0", "test1"), null);
        PredicateBucket<String, List<String>> bucket1 = new PredicateBucket<>(Arrays.asList("test2", "test3"), null);

        BucketManager<String, PredicateBucket<String, List<String>>> manager = new BucketManager<>(Stream.of(bucket0, bucket1));
        Map<PredicateBucket<String, List<String>>, Stream<String>> map = manager.compoundStream();
        assertTrue(map.containsKey(bucket0));
        assertTrue(map.containsKey(bucket1));

        String string0 = map.get(bucket0).collect(Collectors.joining());
        assertEquals("test0test1", string0);

        String string1 = map.get(bucket1).collect(Collectors.joining());
        assertEquals("test2test3", string1);
    }

    @Test
    void bucketCount() {
        assertEquals(1, new BucketManager<>(Stream.of(new PredicateBucket<>(new ArrayList<>(), null))).bucketCount());
    }

    @Test
    void elementCount() {
        PredicateBucket<String, List<String>> bucket0 = new PredicateBucket<>(Arrays.asList("test0", "test1"), null);
        PredicateBucket<String, List<String>> bucket1 = new PredicateBucket<>(Arrays.asList("test2", "test3"), null);

        assertEquals(4, new BucketManager<>(Stream.of(bucket0, bucket1)).elementCount());
    }

    @Test
    void elementStream() {
        PredicateBucket<String, List<String>> bucket0 = new PredicateBucket<>(Arrays.asList("test0", "test1"), null);
        PredicateBucket<String, List<String>> bucket1 = new PredicateBucket<>(Arrays.asList("test2", "test3"), null);

        Set<String> stringStream = new BucketManager<>(Stream.of(bucket0, bucket1)).elementStream().collect(Collectors.toSet());
        assertEquals(4, stringStream.size());
        assertTrue(stringStream.contains("test0"));
        assertTrue(stringStream.contains("test1"));
        assertTrue(stringStream.contains("test2"));
        assertTrue(stringStream.contains("test3"));
    }

    @Test
    void clear() {
        PredicateBucket<String, List<String>> bucket0 = new PredicateBucket<>(Arrays.asList("test0", "test1"), null);
        PredicateBucket<String, List<String>> bucket1 = new PredicateBucket<>(Arrays.asList("test2", "test3"), null);

        BucketManager<String, PredicateBucket<String, List<String>>> manager = new BucketManager<>(Stream.of(bucket0, bucket1));
        manager.clear();

        manager.buckets().forEach(bucket -> assertTrue(bucket.collection.isEmpty()));
    }

    @Test
    void removeAll() {
        ArrayList<String> list = new ArrayList<>();
        list.add("test0");
        list.add("test1");

        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(list, s -> s.startsWith("test"));
        BucketManager<String, PredicateBucket<String, List<String>>> manager = new BucketManager<>(Stream.of(bucket));

        Map<String, Set<PredicateBucket<String, List<String>>>> test = manager.removeAll(Stream.of("test0", "test1"));
        assertEquals(2, test.size());
        assertTrue(new ArrayList<>(test.get("test0")).get(0).getCollection().isEmpty());
    }

    @Test
    void remove() {
        /*we construct an ArrayList as opposed to using Collections.singletonList(T t)
        because Collections.singletonList(T t) gives a list which is immutable
  */
        ArrayList<String> list = new ArrayList<>();
        list.add("test0");

        PredicateBucket<String, List<String>> bucket = new PredicateBucket<>(list, s -> s.startsWith("test"));
        BucketManager<String, PredicateBucket<String, List<String>>> manager = new BucketManager<>(Stream.of(bucket));

        Set<PredicateBucket<String, List<String>>> test = manager.remove("test0");
        assertEquals(1, test.size());
        assertTrue(test.contains(bucket));
        assertTrue(new ArrayList<>(test).get(0).getCollection().isEmpty());
    }

    @Test
    void addAll() {
        PredicateBucket<String, ArrayList<String>> bucket0 = new PredicateBucket<>(new ArrayList<>(), s -> s.startsWith("h"));
        PredicateBucket<String, ArrayList<String>> bucket1 = new PredicateBucket<>(new ArrayList<>(), s -> s.startsWith("w"));

        BucketManager<String, PredicateBucket<String, ArrayList<String>>> manager = new BucketManager<>(Stream.of(bucket0, bucket1));
        Map<String, Set<PredicateBucket<String, ArrayList<String>>>> stringSetMap = manager.addAll(Stream.of("hello", "world"));
        assertEquals(2, stringSetMap.size());

        Set<PredicateBucket<String, ArrayList<String>>> hello = stringSetMap.get("hello");
        assertEquals(1, hello.size());
        assertTrue(hello.contains(bucket0));

        Set<PredicateBucket<String, ArrayList<String>>> world = stringSetMap.get("world");
        assertEquals(1, world.size());
        assertTrue(world.contains(bucket1));
    }

    @Test
    void add() {
        PredicateBucket<String, ArrayList<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s -> s.startsWith("test"));
        BucketManager<String, PredicateBucket<String, ArrayList<String>>> manager = new BucketManager<>(Stream.of(bucket));

        Set<PredicateBucket<String, ArrayList<String>>> test = manager.add("test");
        assertEquals(1, test.size());
        assertTrue(new ArrayList<>(test).get(0).collection.contains("test"));
    }

    @Test
    void buckets() {
        PredicateBucket<String, List<String>> bucket0 = new PredicateBucket<>(Arrays.asList("test0", "test1"), null);
        PredicateBucket<String, List<String>> bucket1 = new PredicateBucket<>(Arrays.asList("test2", "test3"), null);

        BucketManager<String, PredicateBucket<String, List<String>>> manager = new BucketManager<>(Stream.of(bucket0, bucket1));
        Set<PredicateBucket<String, List<String>>> buckets = manager.buckets();
        assertEquals(2, buckets.size());
        assertTrue(buckets.contains(bucket0));
        assertTrue(buckets.contains(bucket1));
    }

    @Test
    void add1() {
        PredicateBucket<String, ArrayList<String>> bucket = new PredicateBucket<>(new ArrayList<>(), s1 -> true);
        Function<String, PredicateBucket<String, ArrayList<String>>> testFunction = s -> bucket;

        Set<PredicateBucket<String, ArrayList<String>>> buckets = new BucketManager<>(testFunction).add("test");
        assertEquals(1, buckets.size());

        assertEquals(bucket, new ArrayList<>(buckets).get(0));
        assertEquals(1, bucket.collection.size());
        assertTrue(bucket.collection.contains("test"));
    }
}