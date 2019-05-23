package sellTicket;

import java.util.concurrent.TimeUnit;

public class Test {
    /**
     * java多线程同步锁的使用
     * 示例：三个售票窗口同时出售20张票
     * */
    public static void main(String[] args) {
        /*Station sta1 = new Station("甲站台");
        Station sta2 = new Station("乙站台");
        Station sta3 = new Station("丙站台");
        new Thread(sta1, sta1.getName()).start();
        new Thread(sta2, sta2.getName()).start();
        new Thread(sta3, sta3.getName()).start();*/

        Thread sleeper = new Thread(() -> {
            Thread.currentThread().setName("sleeper");
            System.out.println("hi");
//                TimeUnit.MILLISECONDS.sleep(1000);
                while (true){
                    System.out.println(Thread.currentThread().getName() + "is alive: " + Thread.currentThread().isAlive());
                }

        });
        sleeper.start();

        System.out.println(sleeper.isAlive());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
