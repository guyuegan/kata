import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortPractice {

    public void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    public int[] initArrNegative(int length, int max){
        Random random = new Random();
        int[] num = new int[length];
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(max) - max/2; // -max/2 -- max/2
        }

        return num;
    }

    public int[] initArrPositive(int length, int max){
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
        int[] arr = initArrNegative(10, 20);
        System.out.println("sort before: " + Arrays.toString(arr));
//        bubblePractice(arr);
//        choosePractice(arr);
        insertPractice(arr);
        System.out.println("sort after: " + Arrays.toString(arr));
    }

    //冒
    public void bubblePractice(int[] arr) {
        int total = arr.length;
        /*每次遍历都能排好一个值，最后一个值不需要再比较，所以边界是total-1*/
        for (int i = 0; i < total-1; i++) {
            /**大值下沉*/
           /* for (int j = 0; j < total-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }*/

           /**小值上浮*/
            for (int j = total-1; j > i; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j, j-1);
                }
            }
        }
    }

    //择
    public void choosePractice(int[] arr) {
        int total = arr.length;
        for (int i = 0; i < total-1; i++) {
            for (int j = i+1; j < total; j++) {
                if (arr[j] < arr[i]) {
                    swap(arr, j, i);
                }
            }
        }
    }

    //入
    public void insertPractice(int[] arr) {
        int total = arr.length;
        for (int i = 1; i < total; i++) { //假设第一个已排好序
            int cur = arr[i]; //备份当前数
            for (int j = i-1; j >= 0; j--) { //从当前数前一个位置开始，都是已排好序的数
                if (cur >= arr[j]) {
                    break; //如果当前数不小于已排序数，直接跳出循环
                }

                /**当前数比已排序的数小，把已排序的数往后移，空出来的位置插入当前数，然后接着往前比*/
                arr[j+1] = arr[j];
                arr[j] = cur;
            }
        }
    }

}
