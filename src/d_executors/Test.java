package d_executors;

import java.util.concurrent.*;

// 1. Разные типы пулов
// 2. Альтернатива join
public class Test {
    public static void main(String[] args) throws InterruptedException {

       ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);


        for (int i = 0; i < 5; i++) {
            int index = i;
            executor.scheduleWithFixedDelay( ()-> {
                try {
                    Thread.sleep(500);
                    System.out.println("Hello " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 2000, 2000, TimeUnit.MILLISECONDS);
        }

        //
        Thread.sleep(20_000);
        executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
        System.out.println("End");
    }
}
