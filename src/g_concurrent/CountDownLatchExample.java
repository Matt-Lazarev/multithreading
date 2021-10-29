package g_concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        Task t = new Task(countDownLatch);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        for(int i=0; i<5; i++){
            executor.execute(new Thread(t));
        }

        executor.shutdown();

        countDownLatch.await();
        System.out.println("Method main");
    }
}

class Task implements Runnable {
    private final CountDownLatch countDownLatch;

    public Task(CountDownLatch count){
        countDownLatch = count;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is finished");
            countDownLatch.countDown();
        }
        catch (InterruptedException ex){
            System.out.println(ex);
        }

    }
}
