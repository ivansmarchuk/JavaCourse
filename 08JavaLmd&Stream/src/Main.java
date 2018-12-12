
interface Executable{
    int execute(int x);
}


class Runner{
    public void run(Executable e){
        int a = e.execute(10);
        System.out.println(a);
    }
}



public class Main {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> System.out.println("Hello world!"));

        Runner runner = new Runner();

        runner.run(new Executable() {
            @Override
            public int execute(int x) {
                System.out.println("Hello Std");
                return x+5;
            }
        });

        runner.run(x -> x);

    }


}
