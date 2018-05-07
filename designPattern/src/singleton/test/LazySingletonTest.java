package singleton.test;

/**
 * <pre>
 * 功    能: 懒汉式单例
 * 涉及版本:
 * 创 建 者: Neo
 * 日    期: 2018年05月07日  10:01:56
 * Q    Q: 1784286916
 * </pre>
 */

import org.junit.Assert;
import org.junit.Test;
import singleton.main.LazySingleton;

/***
 * 单例类只有一个实例
 * 单例类自己内部创建这个实例
 * 单例类向外提供这个实例
 */
public class LazySingletonTest {
    @Test
    public void getInstance() {
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance02 = LazySingleton.getInstance();
        Assert.assertSame(instance, instance02);
    }

}
