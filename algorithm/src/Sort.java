import org.junit.Test;

import java.util.Random;

public class Sort {

    public void printArr(int[] num){
        for (int i = 0; i < num.length; i++) {

            if (i!=0 && i%10==0)
                System.out.println();

            System.out.print(num[i] + "、");
        }
        System.out.println();
    }

    public int[] initArr(int length, int max){
        Random random = new Random();
        int[] num = new int[length];
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(max) - max/2; // -max/2 -- max/2
        }

        return num;
    }

    @Test
    public void testSort(){

        int[] num = initArr(100, 200);

        System.out.println("before sort: ");
        printArr(num);

        num = testBubble(num);
//        num = testSelect(num);

        System.out.println("after sort: ");
        printArr(num);
    }


    //冒：真正的冒泡，有两两对比
    public int[] testBubble(int[] num){
        long start = System.currentTimeMillis();
        int total = num.length;

        for (int i = 0; i < total-1; i++) {
            for (int j = 0; j < total-i-1; j++) {
                if (num[j] > num[j+1]){
                    int tmp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = tmp;
                }
            }
        }

        System.out.print("【耗时：" + (System.currentTimeMillis() - start) + "】");
        return num;
    }


    //择
    public int[] testSelect(int[] num){
        long start = System.currentTimeMillis();
        int total = num.length;
        /**
         * 数组中从头到尾取每一个数，和它之后的每一个数比较，
         * 如果后面的数小于当前数，则将后面的数和当前数调换位置
         */
        for (int i = 0; i < total-1; i++) {
            for (int j = i+1; j < total; j++) {
                if (num[j] < num[i]){
                    int tmp = num[i];
                    num[i] = num[j];
                    num[j] = tmp;
                }
            }
        }

        System.out.print("【耗时：" + (System.currentTimeMillis() - start) + "】");
        return num;
    }

    //入

    //希

    //快

    //归

    //堆
}
