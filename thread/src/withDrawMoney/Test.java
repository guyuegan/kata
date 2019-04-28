package withDrawMoney;

public class Test {
    public static void main(String[] args) {
        Bank bank = new Bank();
        //xiaoming使用柜台取钱
        Runnable xiaoming = () -> {
            while (Bank.money >= 100) {
                bank.counter(100);
            }
        };

        //xiaoli使用ATM取钱
        Runnable xiaoli = () -> {
            while (Bank.money >= 200) {
                bank.ATM(200);
            }
        };

        // 两个人开始取钱
        new Thread(xiaoming, "xiaoming").start();
        new Thread(xiaoli, "xiaoli").start();
    }
}
