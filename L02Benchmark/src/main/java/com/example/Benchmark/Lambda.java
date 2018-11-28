package com.example.Benchmark;

import java.util.Iterator;

public class Lambda {

    public static void main(String[] args) {
        Operationable operation;
        operation = (x, y) -> x+y;
        Operationable operation3 = (int x, int y)-> x * y;

        int result = operation.calculate(10 , 20);
    }
}

interface Operationable{
    int calculate (int x, int y);



}