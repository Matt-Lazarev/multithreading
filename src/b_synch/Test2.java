package b_synch;

//1. synchronized по объекту, по классу разными способами
//2. Статический метод synchronized
//3. Синхронизация по монитору
public class Test2 {
    public static void main(String[] args) {
        Test2 t1 = new Test2();
        Test2 t2 = new Test2();

        Thread thread1 = new Thread(t1::work);
        Thread thread2 = new Thread(t2::work);

        thread1.start();
        thread2.start();
    }

    public void work() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work is done!");
    }
}
