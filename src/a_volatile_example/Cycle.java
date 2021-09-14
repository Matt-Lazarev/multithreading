package a_volatile_example;

public class Cycle {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(2000);

        new Thread(thread::stopThread).start();
    }
}

class MyThread extends Thread {
    private volatile boolean stop = false;

    public void run() {
        int j = 0;
        while (!stop) {
            System.out.println("Iteration: " + j);
            j++;
        }
    }

    public void stopThread(){
        this.stop = true;
    }
}
