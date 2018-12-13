package com.example.atm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ATMTest {

    private ATM atm;

    @Before
    public void setUp() throws Exception {
        atm = new ATM();
    }

    @Test
    public void shouldDepositNotesOfDifferentDenomimations(){
        atm.deposit(10, 100);
        atm.deposit(5, 500);
        int balance = atm.getBalance();
        Assert.assertEquals(3500, balance);
    }

    @Test
    public void shouldWithdrawAmountByMinimumOfNotes(){
        atm.deposit(10, 100);
        atm.deposit(10, 500);
        Map<Integer, Integer> withdrawal = atm.withdraw(700);
        Assert.assertEquals(5300, atm.getBalance());
        Assert.assertEquals(2, withdrawal.get(100).intValue());
        Assert.assertEquals(1, withdrawal.get(500).intValue());
    }

    @Test(expected = RuntimeException.class)
    public void schouldThrowExceptionWhenCantDispense(){
        atm.deposit(10, 500);
        atm.deposit(2, 100);
        atm.withdraw(800);
    }

    @Test
    public void schouldNotDispanseMoreThanThereIsNotes(){
        atm.deposit(1, 1000);
        atm.deposit(10, 500);
        int balanceBefore = atm.getBalance();
        Map<Integer, Integer> withdrawal = atm.withdraw(2500);
        Assert.assertEquals(balanceBefore - 2500, atm.getBalance());
        Assert.assertEquals(3, withdrawal.get(500).intValue());
        Assert.assertEquals(1, withdrawal.get(1000).intValue());

    }
}
