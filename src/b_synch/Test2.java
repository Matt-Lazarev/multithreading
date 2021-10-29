package b_synch;

import java.util.concurrent.atomic.AtomicInteger;

// 1. Проблемы кода: counter == 0
// 2. Состояние гонки
// 3. Исправление с помощью синхронизации
// 4. Атомарные переменные
public class Test2 {
    private AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        new Test2().count();
    }

    private void inc() {
        counter.incrementAndGet();
    }

    public void count() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                inc();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                inc();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
    }


}
