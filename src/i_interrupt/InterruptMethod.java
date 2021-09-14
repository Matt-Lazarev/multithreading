package i_interrupt;

// 1. Deprecated
// 2. Interrupt method
// 3. Interrupt sleep
public class InterruptMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(InterruptMethod::infiniteMethod);
        t.start();

        Thread.sleep(4000);
        t.interrupt();

        t.join();

        System.out.println("End");
    }


    public static void infiniteMethod() {
        long i = 0;
        while (true) {
            if (i % 1_000_000_000 == 0) {
                System.out.println("Iteration " + i);
            }
            i++;

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.out.println("Interrupted " + i);
                break;
            }
        }
    }
}
