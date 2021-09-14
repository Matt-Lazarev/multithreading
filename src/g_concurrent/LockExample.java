package g_concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//1. Без лока
//2. Lock
//3. Исключение внутри синхронизированного блока
public class LockExample {

    private static final Lock lock = new ReentrantLock(true);
    static volatile int count;

    public static void increment(){
        lock.lock();
        try { count++; }
        finally { lock.unlock(); }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i=0; i<10_000; i++){
                increment();;
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0; i<10_000; i++){
                increment();
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


        System.out.println(count);
    }

}
