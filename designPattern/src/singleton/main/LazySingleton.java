package singleton.main; /**
 * <pre>
 * 功    能: 懒汉式单例
 * 涉及版本:
 * 创 建 者: Neo
 * 日    期: 2018年05月07日  09:25:05
 * Q    Q: 1784286916
 * </pre>
 */

/***
 * 单例模式三个特点：
 *
 * 1.单例类只能有一个实例
 * 2.单例类必须内部创建唯一的实例
 * 3.单例类必须给所有对象提供这一实例
 */

/**单例模式确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例。*/

/***
 * 每台计算机可以有若干个打印机，
 * 但只能有一个Printer Spooler，
 * 以避免两个打印作业同时输出到打印机中。
 *
 */
//懒汉式单例，在第一次调用的时候实例化自己
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton(){}

    //静态工厂方法
    public static LazySingleton getInstance(){
        if (lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return lazySingleton;
    }
}
