package sellTicket;

public class Station implements Runnable {

    private String name;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    // 为了保持票数的一致，票数要静态
    private static int ticketNum = 100;
    // 创建一个静态钥匙，值是任意的
    private static Object key = "key";

    // 重写run方法，实现卖票操作
    @Override
    public void run() {
        while (ticketNum > 0) {
            // 这个很重要，必须使用一个锁，
            // 进去的人会把钥匙拿在手上，出来后才把钥匙让出来
            synchronized (key) {
                if (ticketNum > 0)
                    System.out.println(name + "卖出了第" + ticketNum-- + "张票");
                else
                    System.out.println("票卖完了");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
