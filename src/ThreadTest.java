import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public synchronized void method1() {
        System.out.println();
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void method2() {
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ThreadTest threadTest = new ThreadTest();
        new Thread(threadTest::method1, "thread-1").start();
        new Thread(threadTest::method2, "thread-2").start();
    }
}
