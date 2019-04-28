package withDrawMoney;

public class Bank {
    // 假设一个账户有1000块钱
    static int money = 1000;

    /*柜台取钱*/
    public void counter(int money) {
        Bank.money -= money;
        System.out.println(Thread.currentThread().getName() + "取走了"+money+",还剩"+Bank.money);
    }

    /*柜台取钱*/
    public void ATM(int money) {
        Bank.money -= money;
        System.out.println(Thread.currentThread().getName() + "取走了"+money+",还剩"+Bank.money);
    }
}
