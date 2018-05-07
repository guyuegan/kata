package singleton.test;

import singleton.main.LazySingleton;

/**
 * <pre>
 * 功    能: $comment$
 * 涉及版本:
 * 创 建 者: Neo
 * 日    期: 2018年05月07日  11:26:29
 * Q    Q: 1784286916
 * </pre>
 */
public class LazySingletonMutilThreadTest {

    public static void main(String[] args) {
        SubThread subThread = new LazySingletonMutilThreadTest().new SubThread();
        new Thread(subThread,"st01").start();
        new Thread(subThread,"st02").start();
        new Thread(subThread,"st03").start();
        new Thread(subThread,"st04").start();
        new Thread(subThread,"st05").start();
        new Thread(subThread,"st06").start();
        new Thread(subThread,"st07").start();
        new Thread(subThread,"st08").start();
        new Thread(subThread,"st09").start();
        new Thread(subThread,"st10").start();
    }

    //子线程
    class SubThread implements Runnable {
        @Override
        public void run() {
                System.out.println(Thread.currentThread().getName() +
                        ":" + LazySingleton.getInstance());
        }

    }
}
