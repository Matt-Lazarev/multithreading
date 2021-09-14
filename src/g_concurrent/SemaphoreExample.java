package g_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// 1. Ошибка кода - synchronized
// 2. Почему не работает синхронизация
// 3. Semaphore
// 4. Ошибка подсчета коннектов
// 5. Потенциальное исключение
public class SemaphoreExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for(int i=0; i<1; i++){
            executor.execute(Connection::connect);
        }

        executor.shutdown();
    }
}

class Connection {
    private static final int MAX_CONNECTIONS = 5;
    private static int connections;

    private static final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS);

    public static void connect() {
        try {
            semaphore.acquire();
            connectPrivate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }

    private static void connectPrivate() throws InterruptedException {
        synchronized (Connection.class){
            connections++;
            System.out.println("Current connections: " + connections);
        }

        Thread.sleep(2000);

        synchronized (Connection.class){
            connections--;
        }
    }
}