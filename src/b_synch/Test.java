package b_synch;

//1. synchronized по объекту, по классу разными способами
//2. Статический метод synchronized
//3. Синхронизация по монитору
public class Test {
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private volatile int field1;
    private volatile int field2;

    public static void main(String[] args) throws InterruptedException {
        Test t1 = new Test();
        //Test t2 = new Test();

        Thread thread1 = new Thread(t1::work);
        Thread thread2 = new Thread(t1::work2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(t1.field1);
        System.out.println(t1.field2);
    }

    public void work() {
        synchronized (monitor1){
           field1 = 10;
        }
    }

    public void work2() {
        synchronized (monitor2){
           field2 = 100;
        }
    }
}
