package com.example.atm;

import java.util.*;

public class ATM {

    int balance = 0;
    SortedMap<Integer, Integer> dispensers = new TreeMap<>(Comparator.reverseOrder());

    public void deposit(int notes, int denomination) {
        int oldValue = dispensers.getOrDefault(denomination, 0);
        dispensers.put(denomination, oldValue + notes);

    }

    public int getBalance() {
        return   dispensers.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    public Map<Integer, Integer> withdraw(final int amount) {
        Map<Integer, Integer> withdrawal = new HashMap<>();
        int remaining = amount;
        for (Integer denomination: dispensers.keySet()){
            int notesThereIs = dispensers.get(denomination);
            int notesWantToDispense = remaining / denomination;
            int notesToDispense = notesWantToDispense  > notesThereIs
                    ? notesThereIs
                    : notesWantToDispense;
            withdrawal.put(denomination, notesToDispense);
            //dispensers.compute(denomination, (key, oldValue) -> oldValue - notesToDispense);
            remaining -= notesToDispense * denomination;
        }

        if (remaining > 0){
            throw new RuntimeException(String.format("Cant dispense %d with dispensers %s", remaining, dispensers));
        }
        withdrawal.forEach((denomination, notesDispensed) ->
                dispensers.compute(denomination, (key, oldValue) -> oldValue - notesDispensed)
                );

        return withdrawal;
    }
}
