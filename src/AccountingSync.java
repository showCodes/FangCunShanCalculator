public class AccountingSync implements Runnable{
    //共享资源(临界资源)
    private static int i=0;

    /**
     * synchronized 修饰实例方法
     */
    private synchronized void increase(){
        i++;
//        System.out.println(Thread.currentThread().getName() + " " + i);
    }

    public int getI() {
        return i;
    }

    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
}