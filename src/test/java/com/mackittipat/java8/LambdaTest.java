package com.mackittipat.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaTest {

    @Test
    public void testLambdaRunnable() {
        Thread t = new Thread(() -> {
            for(int i=0; i<10; i++) {
                System.out.println(i);
            }
        });
        t.start();
    }

    @Test
    public void testLambdaComparator() {
        String[] array = new String[] {"Alpha", "Beta", "Gramma", "Delta", "Epsilon"};
        Arrays.sort(
                array,
                (o1, o2) -> Integer.compare(o1.length(), o2.length())
        );

        Assert.assertArrayEquals(
                new String[] {"Beta", "Alpha", "Delta", "Gramma", "Epsilon"},
                array
        );
    }

    @Test
    public void testLambdaFunctionalInterface() {
        String[] array = new String[] {"Alpha", "Beta", "Gramma", "Delta", "Epsilon"};
        Comparator<String> comparator = (o1, o2) -> Integer.compare(o1.length(), o2.length());
        Arrays.sort(
                array,
                comparator
        );

        Assert.assertArrayEquals(
                new String[] {"Beta", "Alpha", "Delta", "Gramma", "Epsilon"},
                array
        );
    }

}
