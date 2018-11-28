package com.example.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    private static final int MEASURE_COUNT = 1000;
    private static final int WARMUP_COUNT = 100000;

    public static void main(String[] args) {
       Collection<Integer> example = new HashSet<>();

        int min = 0;
        int max = 9999999;

        for (int i = min; i < max + 1; i++) {
            example.add(i);
        }

        calcTime(()->example.contains(max));
    }

    private static void calcTime (Runnable runnable){
        long startTime = System.nanoTime();
        for (int i = 0; i < WARMUP_COUNT; i++) {
            runnable.run();
        }
        for (int i = 0; i < MEASURE_COUNT; i++) {
            runnable.run();
        }
        long finishTime = System.nanoTime();
        long timeNs = (finishTime - startTime)/MEASURE_COUNT;
        System.out.println("Time spent: " + timeNs + "ns (" + timeNs/1000000 + "ms)");

    }
}
