package com.mackittipat.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MethodReferenceTest {

    @Test
    public void testObjectInstanceMethod() {
        Consumer<String> c1 = s -> System.out.println(s);
        Consumer<String> c2 = System.out::println;
    }


    @Test
    public void testClassStaticMethod() {
        BiFunction<Double, Double, Double> bi1 = (a, b) -> Math.pow(a, b);
        BiFunction<Double, Double, Double> bi2 = Math::pow;
    }

    @Test
    public void testClassInstanceMethod() {
        String[] words = new String[] {"Aaa", "Bbb", "Ccc"};
        Arrays.sort(words, (s1, s2) -> s1.compareTo(s2));
        Arrays.sort(words, String::compareTo);
    }
}
