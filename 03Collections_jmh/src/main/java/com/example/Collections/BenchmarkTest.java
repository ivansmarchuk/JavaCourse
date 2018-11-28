package com.example.Collections;

import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Fork(1)
public class BenchmarkTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        Collection<Integer> example = new HashSet<>();
        int max = 9999999;

        @Setup
        public void initState(){
            int min = 0;
            for (int i = min; i < max + 1; i++) {
                example.add(i);
            }
        }
    }

    @Benchmark
    public boolean test(BenchmarkState state) {
        return state.example.contains(state.max);
    }

}
