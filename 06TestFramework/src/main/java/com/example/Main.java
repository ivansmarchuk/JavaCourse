package com.example;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Hello"));
    }
}
