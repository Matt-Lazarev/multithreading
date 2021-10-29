package f_producer_consumer_pt2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//1. Замена очереди на блокирующую очередь
public class Main {
    private static BlockingQueue<Integer> queue =
                            new ArrayBlockingQueue<>(7);

    public static void produce() throws InterruptedException {
        while (true) {
            queue.put(new Random().nextInt(100));
        }
    }

    public static void consume() throws InterruptedException {
        while (true) {
            int element = queue.take();
            System.out.println(element + "\tQueue size: " + queue.size());
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
