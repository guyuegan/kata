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
//        choose(numArr);
//        insert(numArr);
        shell(numArr);
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

    //入
    public void insert(int[] numArr) {
        /**未排序元素[当前元素]和已排序元素[当前元素前一个元素]对比，
         * 如果当前元素小于已排序元素的元素，就将已排序元素后移一位，
         * 当前元素再和更前面的已排序元素比较，直到当前元素不再小于已排序元素，
         * 那么当前元素插入到令比较停止的元素的后面一位*/
        for (int i = 1; i < numArr.length; i++) {
            int curNum = numArr[i];
            int preIdx = i-1;

            while (preIdx>=0 && curNum<numArr[preIdx]) {
                numArr[preIdx+1] = numArr[preIdx];
                preIdx--;
            }
            numArr[preIdx+1] = curNum;
        }
    }

    //希
    public void shell(int[] numArr) {
        /**跨指定间隔使用插入法，一般是元素数量递归对半取间隔
         * 策略1：取整个序列符合每次间隔的子序列使用插入法
         * 策略2：根据间隔的跨度，依次取子序列，对这些系列使用插入法*/
        for (int gap = numArr.length>>1; gap >= 1; gap >>= 1) {

            /*for (int i = gap; i < numArr.length; i+=gap) {
                int curNum = numArr[i];
                int preIdx = i - gap;

                while(preIdx>=0 && curNum<numArr[preIdx]) {
                    numArr[preIdx+gap] = numArr[preIdx];
                    preIdx -= gap;
                }
                numArr[preIdx+gap] = curNum;
            }*/

            /**更快？？*/
            for (int i = 0; i < gap; i++) {
                for (int j = gap+i; j < numArr.length; j+=gap) {
                    int curNum = numArr[j];
                    int preIdx = j-gap;

                    while (preIdx>=0 && curNum<numArr[preIdx]) {
                        numArr[preIdx+gap] = numArr[preIdx];
                        preIdx -= gap;
                    }
                    numArr[preIdx+gap] = curNum;
                }
            }
        }
    }
}
