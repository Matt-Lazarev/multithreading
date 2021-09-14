package d_executors;

import java.util.concurrent.*;

// 1. Разные типы пулов
// 2. Альтернатива join
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        for (int i = 0; i < 5; i++) {
            int index = i;
            executor.schedule(() -> System.out.println("Hello " + index),
                    1000, TimeUnit.MILLISECONDS);
        }

        Thread.sleep(10000);

        executor.shutdown();

        boolean isOver = executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("End");
    }
}
