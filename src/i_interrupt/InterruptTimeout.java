package i_interrupt;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 1. Interrupt after timeout
public class InterruptTimeout {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(InterruptTimeout::infiniteMethod);
        t.start();

        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);

        executor.schedule(InterruptTimeout::stopThread, 3000, TimeUnit.MILLISECONDS);

        t.join();

        executor.shutdown();
        System.out.println("End");

    }

    private static boolean stop = false;

    public static void infiniteMethod() {
        long i = 0;
        while (!stop) {
            if (i % 700_000_000 == 0) {
                System.out.println("Iteration " + i);
            }
            i++;
        }
    }

    public static void stopThread(){
        stop = true;
    }
}
