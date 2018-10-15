package com.mackittipat.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    // Create

    @Test
    public void testCreation() {
        Stream<String> sourceOf = Stream.of("Alpha", "Beta", "Gramma", "Delta", "Epsilon");

        // Create Stream from Array
        String[] words = new String[] {"Alpha", "Beta", "Gramma", "Delta", "Epsilon"};
        Stream<String> sourceArray = Stream.of(words);

        // Create Stream from Collection
        List<String> wordList = Arrays.asList("Alpha", "Beta", "Gramma", "Delta", "Epsilon");
        Stream<String> sourceList = wordList.stream();
    }

    // Transform

    @Test
    public void testFilter() {
        Stream<String> source = Stream.of("Alpha", "Beta", "Gramma", "Delta", "Epsilon")
                .filter(s -> s.length() > 5);
        Assert.assertArrayEquals(
                new String[] {"Gramma", "Epsilon"},
                source.toArray());
    }

    @Test
    public void testMap() {
        Stream<String> source = Stream.of("Alpha", "Beta", "Gramma", "Delta", "Epsilon")
                .map(s -> String.valueOf(s.charAt(0)));
        Assert.assertArrayEquals(
                new String[] {"A", "B", "G", "D", "E"},
                source.toArray());
    }

    @Test
    public void testFlatMap() {
        Stream<String> source = Stream.of("Alpha", "Beta")
                .flatMap(s -> Stream.of(s.split(""))); // flatMap require anything that return Stream.
        Assert.assertArrayEquals(
                new String[] {"A", "l", "p", "h", "a", "B", "e", "t", "a"},
                source.toArray());
    }

    @Test
    public void testLimit() {
        Stream<Integer> source = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).limit(3);
        Assert.assertArrayEquals(
                new Integer[] {0, 1, 2},
                source.toArray());
    }

    @Test
    public void testSkip() {
        Stream<Integer> source = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).skip(7);
        Assert.assertArrayEquals(
                new Integer[] {7, 8, 9},
                source.toArray());
    }

    @Test
    public void testConcat() {
        Stream<String> source1 = Stream.of("Alpha", "Beta");
        Stream<String> source2 = Stream.of("Gramma", "Delta", "Epsilon");
        Stream<String> source3 = Stream.concat(source1, source2);
        Assert.assertArrayEquals(
                new String[] {"Alpha", "Beta", "Gramma", "Delta", "Epsilon"},
                source3.toArray());
    }

    @Test
    public void testDistinct() {
        Stream<Integer> source = Stream.of(1, 2, 3, 3, 2, 1, 4, 5, 4)
                .distinct();
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5},
                source.toArray());
    }

    @Test
    public void testSortedString() {
        Stream<String> source = Stream.of("Beta", "Gramma", "Delta", "Alpha", "Epsilon")
                .sorted();
        Assert.assertArrayEquals(
                new String[] {"Alpha", "Beta", "Delta", "Epsilon", "Gramma"},
                source.toArray());

        source = Stream.of("Alpha", "Beta", "Gramma", "Delta", "Epsilon")
                .sorted(Comparator.comparing(String::length));
        Assert.assertArrayEquals(
                new String[] {"Beta", "Alpha",  "Delta", "Gramma", "Epsilon"},
                source.toArray());

        source = Stream.of("Alpha", "Beta", "Gramma", "Delta", "Epsilon")
                .sorted(Comparator.comparing(String::length).reversed());
        Assert.assertArrayEquals(
                new String[] {"Epsilon", "Gramma", "Alpha",  "Delta", "Beta"},
                source.toArray());
    }

    @Test
    public void testSortedInt() {
        Stream<Integer> source3 = Stream.of(2, 1, 4, 5, 3)
                .sorted();
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5},
                source3.toArray());

        Stream<Integer> source4 = Stream.of(2, 1, 4, 5, 3)
                .sorted((i1, i2) -> i1 - i2);
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5},
                source4.toArray());

        Stream<Integer> source5 = Stream.of(2, 1, 4, 5, 3)
                .sorted((i1, i2) -> i2 - i1);
        Assert.assertArrayEquals(
                new Integer[] {5, 4, 3, 2, 1},
                source5.toArray());

        Stream<Integer> source6 = Stream.of(2, 1, 4, 5, 3)
                .sorted(Comparator.comparingInt(i -> i));
        Assert.assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5},
                source6.toArray());
    }

    @Test
    public void testPeek() {
        Stream.of("Alpha", "Beta", "Gramma", "Delta", "Epsilon")
                .peek(s -> System.out.println("Debug : " + s))
                .limit(3)
                .toArray();
    }

    // Reduction

    @Test
    public void testMin() {
        Stream<Integer> source = Stream.of(2, 5, 1, 4, 3);
        Optional<Integer> result = source.min((i1, i2) -> i1 - i2);
        result.ifPresent(i -> Assert.assertEquals(Integer.valueOf(1), i));
    }

    @Test
    public void testMax() {
        Stream<Integer> source = Stream.of(2, 5, 1, 4, 3);
        Optional<Integer> result = source.max((i1, i2) -> i1 - i2);
        result.ifPresent(i -> Assert.assertEquals(Integer.valueOf(5), i));
    }

    @Test
    public void testFindFirst() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        Optional<String> result = source.filter(s -> s.startsWith("L"))
                .findFirst();
        Assert.assertEquals("Lisbon", result.get());
    }

    @Test
    public void testAntMatch() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        boolean result = source.anyMatch(s -> s.startsWith("C"));
        Assert.assertFalse(result);
    }

    // Collecting result

    @Test
    public void testToArray() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        String[] results = source.toArray(String[]::new);
        Assert.assertArrayEquals(new String[] {"Bangkok", "Lisbon", "Atlanta", "Madrid", "London"}, results);
    }

    @Test
    public void testCollectToList() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        List<String> results = source.collect(Collectors.toList());
        Assert.assertArrayEquals(new String[] {"Bangkok", "Lisbon", "Atlanta", "Madrid", "London"}, results.toArray());
    }

    @Test
    public void testCollectToSet() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        // Collectors.toSet() return HashSet, no guarantee insertion order
        Set<String> results = source.collect(Collectors.toSet());
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testCollectToMap() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "Amsterdam");
        Map<String, Integer> results = source.collect(
                Collectors.toMap(
                        k -> k,
                        v -> v.length()
                ));
        Assert.assertEquals(5, results.size());
        Assert.assertEquals(7, results.get("Bangkok").intValue());
        Assert.assertEquals(6, results.get("Lisbon").intValue());
        Assert.assertEquals(7, results.get("Atlanta").intValue());
        Assert.assertEquals(6, results.get("Madrid").intValue());
        Assert.assertEquals(9, results.get("Amsterdam").intValue());
    }

    @Test
    public void testCollectToMapFunctionIdentity() {
        Stream<String> source = Stream.of("Paris", "Madrid", "Bangkok", "Amsterdam");
        Map<Integer, String> results = source.collect(
                Collectors.toMap(
                        k -> k.length(),
                        Function.identity() // Return input argument, which is City name
                ));
        Assert.assertEquals(4, results.size());
        Assert.assertEquals("Paris", results.get(5));
        Assert.assertEquals("Madrid", results.get(6));
        Assert.assertEquals("Bangkok", results.get(7));
        Assert.assertEquals("Amsterdam", results.get(9));
    }

    @Test
    public void testCollectToMapDuplicateKey() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "Amsterdam");
        Map<Integer, String> results = source.collect(
                Collectors.toMap(
                        k -> k.length(),
                        v -> v,
                        (e, n) -> e // If key duplicate, don't change value of key.
                ));

        Assert.assertEquals(3, results.size());
        Assert.assertEquals("Lisbon", results.get(6));
        Assert.assertEquals("Bangkok", results.get(7));
        Assert.assertEquals("Amsterdam", results.get(9));
    }

    @Test
    public void testCollectToCollection() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        // LinkedHashSet guarantee insertion order
        Set<String> results = source.collect(Collectors.toCollection(LinkedHashSet::new));
        Assert.assertArrayEquals(new String[] {"Bangkok", "Lisbon", "Atlanta", "Madrid", "London"}, results.toArray());
    }

    @Test
    public void testCollectString() {
        Stream<String> source = Stream.of("A", "B", "C", "D");
        String result = source.collect(Collectors.joining());
        Assert.assertEquals("ABCD", result);
    }

    @Test
    public void testCollectSummarizingInt() {
        Stream<Integer> source = Stream.of(1, 2, 2, 3, 3, 3, 4);
        IntSummaryStatistics intSumStat = source.collect(Collectors.summarizingInt(Integer::intValue));
        Assert.assertEquals(7, intSumStat.getCount());
        Assert.assertEquals(1, intSumStat.getMin());
        Assert.assertEquals(4, intSumStat.getMax());
        Assert.assertEquals(2.57, intSumStat.getAverage(), 0.01);
    }

}
