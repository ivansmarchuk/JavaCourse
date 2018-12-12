package com.example.TestRunner;

public class TestFramework {
    public static void main(String[] args) {

        System.out.println("**Run all tests in [CarTest.class] **");
        B bc = new C();
        char a = 1;
        char b = 2;
        //float c = 1.1;
       // System.out.println(0x1a-0b1010);
        A ab = new B();
        A ac = new C();
        C cc = new C();
       // B bc = new C();


        System.out.println(ac.sum(1,1));

    }

}
interface A{
    int sum (int x, int y);
}
class B implements A{
    @Override
    public int sum(int x, int y) {
        return x+y;
    }
    public int diff(int x, int y){
        return x-y;
    }
}
class C extends B{
    public int mult(int x, int y){
        return x*y;
    }
    public int diff(int x, int y){
        return y-x;
    }

}