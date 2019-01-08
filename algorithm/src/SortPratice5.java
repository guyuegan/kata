import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortPratice5 {
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
            /*下沉法*/
            /*for (int j = 0; j < numArr.length-1-i ; j++) {
                if (numArr[j] < numArr[j+1])
                    swap(numArr, j ,j+1);
            }*/

            /*上升法*/
            for (int j = numArr.length-1; j > i; j--) {
                if (numArr[j] > numArr[j-1])
                    swap(numArr, j, j-1);
            }
        }
    }

    private void choose(int[] numArr) {
        for (int i = 0; i < numArr.length-1; i++) {
            int maxIdx = i;
            for (int j = i+1; j < numArr.length; j++) {
                if (numArr[j] > numArr[maxIdx])
                    maxIdx = j;
            }

            if (i != maxIdx)
                swap(numArr, maxIdx, i);
        }
    }

    private void insert(int[] numArr) {
        int gap = 1;
        for (int i = 1; i < numArr.length; i++) {
            int curData = numArr[i];
            int prevIdx = i-gap;

            while (prevIdx >= 0 && curData > numArr[prevIdx]) {
                numArr[prevIdx+gap] = numArr[prevIdx];
                prevIdx -= gap;
            }

            if (i-gap != prevIdx)
                numArr[prevIdx+gap] = curData;
        }
    }

    private void shell(int[] numArr) {
        for (int gap = numArr.length >> 1; gap > 0; gap >>= 1) {
            for (int i = 0; i < gap; i++) {
                for (int j = gap+i; j < numArr.length; j+=gap) {
                    int curData = numArr[j];
                    int prevIdx = j-gap;

                    while (prevIdx >= 0 && curData > numArr[prevIdx]) {
                        numArr[prevIdx+gap] = numArr[prevIdx];
                        prevIdx -= gap;
                    }

                    if (j-gap != prevIdx)
                        numArr[prevIdx+gap] = curData;
                }
            }
        }
    }
}
