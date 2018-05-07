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
 *  如果一个变量被定义为final，那么这个字段只能够被初始化一次，在初始化后，该变量将不能再被改变。

 如果一个类被定义为final，那么该类不能够被任何类不能够生成任何子类，并且该类中函数也是final类型的。

 如果一个对象的引用被声明为final，说明这个对象的引用不能够再指向其它的对象，但是这个对象本身是可以被修改的。
 */

/***
 * 静态内部类:实现了线程安全，又避免了同步带来的性能影响
 */
public class LazySingleton02 {

    private LazySingleton02(){}

    private static class LazyHolder{
        //final:最终关系【引用关系不改变】
        private static final LazySingleton02 INSTANCE = new LazySingleton02();
    }

    public static final LazySingleton02 getInstance(){
        return LazyHolder.INSTANCE;
    }
}
