package c_synch_monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 1. Без синхронизации - состояние гонки
// 2. Сихронизация с помощью ключевого слова, синхронизация по блоку
// 3. Синхронизация с помощью монитора (одно и двух)
public class Test {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        FillArray obj = new FillArray();

        Thread thread1 = new Thread(obj::fill);
        Thread thread2 = new Thread(obj::fill);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        obj.printArrays();

        System.out.println("Time: " + (double) (System.currentTimeMillis() - start) / 1000 + " sec");
    }
}

class FillArray {
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void fill() {
        for (int i = 0; i < 1000; i++) {
            synchronized (monitor1){
                list1.add(new Random().nextInt(100));
                try{ Thread.sleep(1); } catch (Exception ignored) {}
            }
            synchronized (monitor2) {
                list2.add(new Random().nextInt(100));
                try{ Thread.sleep(1); } catch (Exception ignored) {}
            }
            //try{ Thread.sleep(1); } catch (Exception ignored) {}
        }
    }

    public void printArrays() {
        System.out.println(list1.size());
        System.out.println(list2.size());
    }

    private void addFirstArray() {
        list1.add(new Random().nextInt(100));
        try { Thread.sleep(1); } catch (Exception ignored) { }

    }

    private void addSecondArray() {
        list2.add(new Random().nextInt(100));
        try { Thread.sleep(1); } catch (Exception ignored) { }
    }
}
