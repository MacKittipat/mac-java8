package com.mackittipat.java8;

import org.junit.Test;

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
}
