package g_concurrent;


import java.util.Random;
import java.util.concurrent.*;

// 1. Callable
// 2. Future
// 3. Future get
// 4. ExecutionException
public class CallableExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future =  executor.submit(()->{
            Random r = new Random();

            if(r.nextInt() < 0)
                throw new IllegalArgumentException("Error in call method");
            return r.nextInt(100);
        });

        executor.shutdown();

        int result = -1;
        try {
            result = future.get();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException: " + e.getMessage());
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("End with result " + result);
    }
}
