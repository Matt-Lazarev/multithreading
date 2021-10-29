package j_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 1.Condition
// 2. Проблема if
// 3. Замена на while

public class Account {
    private int balance;

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public Account(int balance) {
        this.balance = balance;
    }

    public static void withdraw(Account from, int amount) {
        lock.lock();
        try {
            while (from.balance < amount) {
                condition.await();
            }

            if(from.balance < 0){
                System.out.println(from.balance);
            }

            from.balance -= amount;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void deposit(Account to, int amount) {
        lock.lock();

        to.balance += amount;
        condition.signal();

        lock.unlock();
    }


    public static void main(String[] args) {
        Account acc = new Account(100_000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000; i++)
                Account.withdraw(acc, 500);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++)
                Account.deposit(acc, 10);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }


        System.out.println(acc.balance);
        System.out.println("end");
    }

}

