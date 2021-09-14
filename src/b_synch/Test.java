package b_synch;

import java.util.concurrent.atomic.AtomicInteger;

// 1. Проблемы кода: join
// 2. Состояние гонки
// 3. Исправление с помощью синхронизации
// 4. Атомарные переменные
public class Test {
    public AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        t.count();

        //Test t2 = new Test();
        //t2.count();
    }

    public void increment() throws InterruptedException {
        if (counter.incrementAndGet() == 200_000) {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void count() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 100_000; i++) {
                    increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 100_000; i++) {
                    increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}
