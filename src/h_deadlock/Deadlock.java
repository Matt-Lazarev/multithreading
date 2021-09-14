package h_deadlock;

public class Deadlock {
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            try {
                new Task().first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()-> {
            try {
                new Task().second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();;
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("The end");
    }
}

class Task{
    private static final Object monitor1 = new Object();
    private static final Object monitor2 = new Object();

    public void first() throws InterruptedException {
        synchronized (monitor1){
            Thread.sleep(1000);
            System.out.println("first");
            second();;
        }
    }
    public void second() throws InterruptedException {
        synchronized (monitor2){
            Thread.sleep(1000);
            System.out.println("second");
            first();
        }
    }
}
