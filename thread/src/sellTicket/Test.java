package sellTicket;

public class Test {
    /**
     * java多线程同步锁的使用
     * 示例：三个售票窗口同时出售20张票
     * */
    public static void main(String[] args) {
        Station sta1 = new Station("甲站台");
        Station sta2 = new Station("乙站台");
        Station sta3 = new Station("丙站台");
        new Thread(sta1, sta1.getName()).start();
        new Thread(sta2, sta2.getName()).start();
        new Thread(sta3, sta3.getName()).start();
    }
}
