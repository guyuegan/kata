import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortPractice2 {
    private void swap(int[] arr, int a, int b) {
        if (a == b) {
            return ;
        }
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    private int[] initArrNegative(int length, int max){
        Random random = new Random();
        int[] num = new int[length];
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(max) - max/2; // -max/2 -- max/2
        }

        return num;
    }

    private int[] initArrPositive(int length, int max){
        Random random = new Random();
        int[] num = new int[length];
        for (int i = 0; i < num.length; i++) {
            if (i%10 == 0) {
                num[i] = 0;
            } else {
                num[i] = random.nextInt(max);
            }
        }

        return num;
    }

    @Test
    public void testSort() {
        int[] numArr = initArrNegative(10, 100);
        System.out.println("before sort: \n" + Arrays.toString(numArr));
//        bubble(numArr);
        choose(numArr);
        System.out.println("after sort: \n" + Arrays.toString(numArr));
    }

    //冒
    public void bubble(int[] numArr) {
        /**最外层的i, 既表明了要比几轮，又记录了已经排序好的元素个数*/
        for (int i = 0; i < numArr.length-1; i++) {
            /**大值下沉*/
            /*for (int j = 0; j < numArr.length-1-i; j++) {
                if (numArr[j] > numArr[j+1])
                    swap(numArr, j, j+1);
            }*/

            /**小值上升*/
            for (int j = numArr.length-1; j > i; j--) {
                if (numArr[j] < numArr[j-1])
                    swap(numArr, j, j-1);
            }
        }
    }

    //择
    public void choose(int[] numArr) {
        for (int i = 0; i < numArr.length; i++) {
            for (int j = i+1; j < numArr.length; j++) {
                if (numArr[i] > numArr[j])
                    swap(numArr, i, j);
            }
        }
    }
}
