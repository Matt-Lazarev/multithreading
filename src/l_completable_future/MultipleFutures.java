package l_completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MultipleFutures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 =
                         CompletableFuture.supplyAsync(() -> "Hello")
                        .thenAccept(System.out::println);

        CompletableFuture<Void> future2 =
                         CompletableFuture.supplyAsync(() -> "Beautiful")
                        .thenAccept(System.out::println);

        CompletableFuture<Void> future3 =
                         CompletableFuture.supplyAsync(() -> "World")
                        .thenAccept(System.out::println);

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
        combinedFuture.get();

    }
}
