package h_deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 1.Race condition
// 2. Добавить synch
// 3. Deadlock
// 4. Один порядок
// 5. Использование Lock
// 6. Ошибка с finally

public class Account {
    private int balance;

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();


    public Account(int balance) {
        this.balance = balance;
    }

    public void transfer(Account from, Account to, int amount) {
        takeLocks(lock1, lock2);
        try {
            if (from.balance >= amount) {
                to.balance += amount;
                from.balance -= amount;
            }
        }
        finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        Account acc1 = new Account(100_000);
        Account acc2 = new Account(200_000);

        Account a = new Account(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++)
                a.transfer(acc1, acc2, 5000);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++)
                a.transfer(acc2, acc1, 5000);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }


        System.out.println(acc1.balance);
        System.out.println(acc2.balance);
        System.out.println("end");
    }


    public static void takeLocks(Lock lock1, Lock lock2) {
        boolean isFirstAvailable = false;
        boolean isSecondAvailable = false;

        while (true) {
            try {
                isFirstAvailable = lock1.tryLock();
                isSecondAvailable = lock2.tryLock();
            } finally {
                if (isFirstAvailable && isSecondAvailable) {
                    return;
                }

                if (isFirstAvailable) {
                    lock1.unlock();
                }

                if (isSecondAvailable) {
                    lock2.unlock();
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
