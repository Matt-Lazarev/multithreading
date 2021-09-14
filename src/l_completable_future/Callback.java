package l_completable_future;

import java.util.concurrent.CompletableFuture;

public class Callback {

    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    sleepSeconds(3);
                    return "Callback class";
                }
        ).thenApply(x -> ("Then apply accepted " + x)
        ).thenAccept(x -> System.out.println("\nThe result is " + x));

        System.out.println("Before sleep");
        try {
            for (int i=0; i<40; i++){
                Thread.sleep(100);
                System.out.print(i);
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("After sleep");
    }


}
