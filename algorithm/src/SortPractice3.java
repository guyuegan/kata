import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortPractice3 {
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
        int max = 100;
        int[] numArr = initArrPositive(10, max);
//        int[] numArr = {12, 66, 14, 75, 98, 97, 58, 23, 82, 26};
        System.out.println("before sort: \n" + Arrays.toString(numArr));
//        bubble(numArr);
//        choose(numArr);
        insert(numArr);
//        shell(numArr);
//        quick(numArr, 0, numArr.length-1);
//        devideAndMerge(numArr);
//        heap(numArr);
//        count(numArr, max);
//        bucket(numArr, max);
//        base(numArr, max);
        System.out.println("after sort: \n" + Arrays.toString(numArr));
    }

    private void bubble(int[] numArr) {
        for (int i = 0; i < numArr.length-1; i++) {
            /**大值下沉*/
            /*for (int j = 0; j < numArr.length - i - 1; j++) {
                if (numArr[j] > numArr[j + 1])
                    swap(numArr, j, j + 1);
            }*/

            /**小值上浮*/
            for (int j = numArr.length-1; j > i; j--) {
                if (numArr[j] < numArr[j - 1])
                    swap(numArr, j, j-1);
            }
        }

    }

    private void choose(int[] numArr) {
        for (int i = 0; i < numArr.length-1; i++) {
            /**要么我最小*/
            int minIdx = i;
            for (int j = i+1; j < numArr.length; j++) {
                if (numArr[j] < numArr[minIdx])
                    /**要么还有人更小*/
                    minIdx = j;
            }

            if (i != minIdx)
                swap(numArr, i, minIdx);
        }
    }

    private void insert(int[] numArr) {
        for (int i = 1; i < numArr.length; i++) {
            int prevIdx = i-1;
            int curData = numArr[i];

            /**记录下让当前值停下的j, j后面一个位置就是当前值插入地方*/
            /*for (int j = i-1; j >= 0; j--) {
                if (curData < numArr[j]) {
                    numArr[j+1] = numArr[j];
                } else {
                    prevIdx = j;
                    break;
                }
            }*/

            while (prevIdx >= 0 && curData < numArr[prevIdx]) {
                numArr[prevIdx+1] = numArr[prevIdx];
                prevIdx--;
            }

            /**将当前值插入到让他停止的下标位置后一位*/
            numArr[prevIdx+1] = curData;
        }
    }
}
