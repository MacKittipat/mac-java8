package com.mackittipat.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalTest {

    // Create

    @Test(expected = NullPointerException.class)
    public void testOf() {
        Optional<String> optString = Optional.of("Hello");
        Assert.assertTrue(optString.isPresent());
        Optional<String> optString2 = Optional.of(null);
    }

    public void testOfNullable() {
        Optional<String> optString = Optional.ofNullable("Hello");
        Assert.assertTrue(optString.isPresent());
        Optional<String> optString2 = Optional.ofNullable(null);
        Assert.assertTrue(optString2.isPresent());
    }

    // Produce an alternative

    @Test
    public void testOrElse() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        Optional<String> optString = source.filter(s -> s.startsWith("T"))
                .findFirst();
        String result = optString.orElse("Tokyo");
        Assert.assertEquals("Tokyo", result);
    }

    @Test(expected = IllegalStateException.class)
    public void testOrElseThrow() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        Optional<String> optString = source.filter(s -> s.startsWith("T"))
                .findFirst();
        optString.orElseThrow(IllegalStateException::new);
    }

    // Consume the value

    @Test
    public void testGet() {
        Stream<String> source = Stream.of("Bangkok", "Lisbon", "Atlanta", "Madrid", "London");
        Optional<String> optString = source.filter(s -> s.startsWith("L"))
                .findFirst();
        String result = "";
        if(optString.isPresent()) {
            result = optString.get();
        }
        Assert.assertEquals("Lisbon", result);
    }

}
