import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        AccountingSync instance=new AccountingSync();
        Thread t1=new Thread(instance, "t1");
        Thread t2=new Thread(instance, "t2");
        t1.start();
        t2.start();
//        t1.join();
//        t2.join();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(instance.getI());
    }
}
