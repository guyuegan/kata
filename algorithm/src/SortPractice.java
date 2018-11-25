import org.junit.Test;

import java.util.*;

public class SortPractice {

    private void swap(int[] arr, int a, int b) {
        if (a == b) {
            return ;
        }
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    @Test
    public void testSwap() {
        int[] arr = {1, 4, 5, 76, 34, 23, 0};
        swap(arr, 0,0);
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
        //int[] arr = initArrNegative(5000, 100000);

        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8, 8, 15, 7, 6, 17, 18, 13, 0, 14, 2, 16, 23, 4, 26, 34, 67, 23,};
//        int[] arr = initArrPositive(20, 20);
//        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
//        int[] arr = {2, 3, 10, 7, 5, 9, 1};

        System.out.println("sort before: " + Arrays.toString(arr));
//        bubblePractice(arr);
//        choosePractice(arr);
//        insertPractice(arr);
            long startTime = new Date().getTime();
//            shellPractice(arr);
//        quickPractice(arr, 0, arr.length-1);
//        mergePractice(arr);
//        heapPractice(arr);
//        countPractice(arr, 18);
        bucketPractice(arr, 70);
            long endTime = new Date().getTime();
            System.out.println("耗时：" + (endTime - startTime));
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
            /**实时插入*/
           /* for (int j = i-1; j >= 0; j--) { //从当前数前一个位置开始，都是已排好序的数
                if (cur >= arr[j]) {
                    break; //如果当前数不小于已排序数，直接跳出循环
                }

                *//**当前数比已排序的数小，把已排序的数往后移，空出来的位置插入当前数，然后接着往前比*//*
                arr[j+1] = arr[j];
                arr[j] = cur;
            }*/

            /**最后插入*/
            int preIdx = i-1;
            while (preIdx >= 0 && arr[preIdx] > cur) {
                arr[preIdx + 1] = arr[preIdx];
                preIdx--;
            }
            arr[preIdx+1] = cur;
        }
    }

    //希
    public void shellPractice(int[] arr) {
        int total = arr.length;
        for (int gap = total/2; gap > 0; gap >>= 1) {

            /*for (int i = gap; i < total; i++) {
                int cur = arr[i];
                for (int j = i-gap; j >= 0; j -= gap) {
                    if (cur >= arr[j]) {
                        break;
                    }
                    arr[j + gap] = arr[j];
                    arr[j] = cur;
                }
            }*/

            /*for (int i = gap; i < total; i++) {
                int cur = arr[i];
                int preIdx = i - gap;
                while (preIdx >= 0 && cur < arr[preIdx]) {
                    arr[preIdx+gap] = arr[preIdx];
                    preIdx -= gap;
                }
                arr[preIdx+gap] = cur;
            }*/

            /**这种更快*/
            for (int i = 0; i < gap; i++) {
                for (int j = gap+i; j < total; j += gap) {
                    int cur = arr[j];
                    int preIdx = j - gap;
                    while (preIdx >= 0 && cur < arr[preIdx]) {
                        arr[preIdx+gap] = arr[preIdx];
                        preIdx -= gap;
                    }
                    arr[preIdx+gap] = cur;
                }
            }
        }
    }

    //快
    public void quickPractice(int[] arr, int left, int right) {
        if (left >= right)
            return;

        //左右哨兵起点
        int l = left, r = right;
        //分区准线
        int base = arr[left];

        /**必须右哨兵先开始找，这样才能确保左右哨兵碰面时，所在位置的数值小于分区准线【将要和分区准线交互的数值，肯定得小于分区准线】*/
        //只有左右哨兵碰面，才会停止分区工作，否则一直工作
        while (l != r) {
            //右哨兵一直向左走，只有找到小于分区准线的数值 或 和左哨兵碰面【一直找不到小于分区准线的数值】才会停下
            while (arr[r] >= base && l != r)
                r --;

            //左哨兵一直向右走，只有找到大于分区准线的数值 或 和右哨兵碰面【一直找不到大于分区准线的数值】才会停下
            while (arr[l] <= base && l != r)
                l ++;

            //左右哨兵都停下了，并且没有碰面，说明当前左右哨兵位置的数值要进行调换
            if (l != r)
                swap(arr, l, r);
        }

        //左右哨兵碰面，分区结束，即分区准线归位
        swap(arr, left, l);

        quickPractice(arr, left, l-1);
        quickPractice(arr, l+1, right);

    }

    //归
    public void mergePractice(int[] arr) {
        /**在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间*/
        int[] temp = new int[arr.length];
        devide(arr, 0, arr.length-1, temp);
    }

    private void devide(int[] arr, int left, int right, int[] temp) {
        if (left >= right)
            return;

        /**递归拆分系列，将系列一分为二*/
        int mid = (left+right)/2;
        devide(arr, left, mid, temp);
        devide(arr, mid+1, right, temp);

        /**分到不能再分，开始左右序列排序*/
        merge(arr, left, right, temp);
    }

    private void merge(int[] arr, int left, int right, int[] temp) {
        int mid = (left+right)/2; //左系列的边界
        int l = left;
        int r = mid+1;
        int idx = 0;

        /**只有排完左序列 或 右序列才能停止*/
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r])
                temp[idx++] = arr[l++];
            else
                temp[idx++] = arr[r++];
        }

        /**如果左序列没有排完*/
        while (l <= mid)
            temp[idx++] = arr[l++];

        /**如果右序列没有排完*/
        while (r <= right)
            temp[idx++] = arr[r++];

        /**左右序列都排完*/
        idx = 0;
        while (left <= right)
            arr[left++] = temp[idx++];
    }

    //堆
    public void heapPractice(int[] arr) {
        int total = arr.length;
         /**首次从下往上调整*/
        for (int fatherIdx = total/2-1; fatherIdx >= 0 ; fatherIdx--) {
            adjustHeap(arr, fatherIdx, total);
        }

         /**将构造成大顶堆之后的根节点，放到最后*/
        for (int lastIdx = total-1; lastIdx > 0; lastIdx--) {
            swap(arr, 0, lastIdx);
             /**发生交换后，重新调整堆结构，由于之前底下的子树都是满足大顶堆，所以从上往下调整*/
            adjustHeap(arr, 0, lastIdx);
        }
    }

    private void adjustHeap(int[] arr, int fatherIdx, int length) {
        int oldFather = arr[fatherIdx];

        for (int leftSonIdx = fatherIdx*2+1; leftSonIdx < length; leftSonIdx = leftSonIdx*2+1) {
            int maxSonIdx = leftSonIdx;
            int rightSonIdx = leftSonIdx+1;

            if (rightSonIdx < length && arr[leftSonIdx] < arr[rightSonIdx])
                maxSonIdx = rightSonIdx;

            if (arr[maxSonIdx] > oldFather) {
                swap(arr, maxSonIdx, fatherIdx);
                /**老大被换下来之后，暂时坐在之前最大儿子的位置上
                 * 调整下面的子树时，下一跳是交换位置下的子节点*/
                leftSonIdx = fatherIdx = maxSonIdx;
            } else {
                break;
            }
        }
    }

    //计
    public void countPractice(int[] arr, int max) {
        int total = arr.length;
        int[] originData = Arrays.copyOf(arr, total);
        int[] countArr = new int[max + 1]; //countArr下标表示排序数值，元素表示出现次数

        //统计排序数值，出现次数
        for (int i = 0; i < total; i++) {
            countArr[originData[i]] ++;
        }

        //根据排序数值出现次数，计算最终排序位置
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        //根据计算结果，调整原始数组各个数值位置
        for (int i = 0; i < total; i++) {
            int curData = originData[i];
            /**countArr中元素值本质表示的是：*/
            int curDataIdx = countArr[curData]-1;
            arr[curDataIdx] = curData;
            /**将当前值的统计位置往前移，因为后面可能还有相同值*/
            countArr[curData]--;
        }
    }

    //桶
    public void bucketPractice(int[] arr, int max) {
        int bucketNum = 0 == max % 10 ? max / 10 : max / 10 + 1;
        Map<Integer, List> allBucket = new HashMap<>(bucketNum);

        for (int i = 0; i < arr.length; i++) {
            int bucketIdx = arr[i] / 10;
            allBucket.get(bucketIdx).add(arr[i]);
        }

        List<Integer> sortBucket = new ArrayList<>(arr.length);
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(allBucket.get(i));
            sortBucket.addAll(allBucket.get(i));
        }

        Integer[] finalArr = new Integer[arr.length];
        sortBucket.toArray(finalArr);
        System.out.println(Arrays.toString(finalArr));
    }
}
