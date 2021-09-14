package e_producer_consumer_pt1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

// 1. Wait & notify
// 2. current thread is not owner
// 3. synchronized

public class Main {
    private static final Object monitor = new Object();
    private static Queue<Integer> queue = new ArrayDeque<>();
    private static final int MAX_SIZE = 10;

    public static void produce() throws InterruptedException {
        while (true) {
            synchronized (monitor) {
                if (queue.size() == MAX_SIZE) {
                    monitor.wait();
                }

                int number = new Random().nextInt(100);
                queue.add(number);
                monitor.notify();
            }
        }
    }

    public static void consume() throws InterruptedException {
        while (true) {
            synchronized (monitor) {
                if (queue.size() == 0) {
                    monitor.wait();
                }

                int number = queue.poll();
                System.out.println("Taken element: " + number + "\tQueue size: " + queue.size());
                monitor.notify();
            }
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


