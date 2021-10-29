package g_concurrent;


import com.sun.jdi.ThreadReference;

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
            int sum = 0;
            for(int i=0; i<5; i++){
                sum += i;
            }
            return sum;
        });

        executor.shutdown();

        int result = -1;
        try {
            result = future.get();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        } catch (ExecutionException e){
            System.out.println("ExecutionException: " + e.getMessage());
        }
        System.out.println("End with result " + result);
    }
}
