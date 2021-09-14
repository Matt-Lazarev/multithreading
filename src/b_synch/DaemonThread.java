package b_synch;

// 1. join
// 2. daemon
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.print (i + " ");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true);

        thread.start();

        Thread.sleep(500);
        System.out.println("\nThread main");

    }
}
