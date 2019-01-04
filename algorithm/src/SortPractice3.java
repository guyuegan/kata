import org.junit.Test;

import java.util.*;

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
        int max = 200;
        int[] numArr = initArrPositive(20, max);
//        int[] numArr = {12, 66, 14, 75, 98, 97, 58, 23, 82, 26};
        System.out.println("before sort: \n" + Arrays.toString(numArr));
//        bubble(numArr);
//        choose(numArr);
//        insert(numArr);
//        shell(numArr);
//        quick(numArr, 0, numArr.length-1);
//        devideAndMerge(numArr);
//        heap(numArr);
//        count(numArr, max);
//        bucket(numArr, max);
        base(numArr, max);
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

            /**记录下让当前值停下的下标, 下标后面一个位置就是当前值插入地方*/
            for (; prevIdx >= 0; prevIdx--) {
                if (curData < numArr[prevIdx]) {
                    numArr[prevIdx+1] = numArr[prevIdx];
                } else {
                    break;
                }
            }

            /*while (prevIdx >= 0 && curData < numArr[prevIdx]) {
                numArr[prevIdx+1] = numArr[prevIdx];
                prevIdx--;
            }*/

            /**将当前值插入到让他停止的下标位置后一位*/
            numArr[prevIdx+1] = curData;
        }
    }

    private void shell(int[] numArr) {
        for (int gap = numArr.length>>1; gap >= 1; gap >>= 1) {
            for (int i = 0; i < gap; i++) {
                for (int j = gap+i; j < numArr.length; j += gap) {
                    int prevIdx = j - gap;
                    int curData = numArr[j];
                    while (prevIdx >= 0 && curData < numArr[prevIdx]) {
                        numArr[prevIdx + gap] = numArr[prevIdx];
                        prevIdx -= gap;
                    }
                    numArr[prevIdx + gap] = curData;
                }
            }
        }
    }

    private void quick(int[] numArr, int left, int right) {
        if (left >= right)
            return;

        int b = left, l = left, r = right;

        /*两个小机器人工作的前提：没有碰面*/
        while (r > l) {
            /*如果没有“=”基准，可能在基准位置停下，很危险（把基准都换掉了）*/
            while (r > l && numArr[r] >= numArr[b])
                r--;
            /*如果没有“=”基准，可能在基准位置停下，很危险（把基准都换掉了）*/
            while (r > l && numArr[l] <= numArr[b])
                l++;

            if (r > l)
                swap(numArr, l, r);
        }
        /*基准归位*/
        swap(numArr, b, l);

        /*在l,r碰面的位置，将系列一分为二*/
        quick(numArr, left, l-1);
        quick(numArr, l+1, right);
    }

    private void devideAndMerge(int[] numArr) {
        /**在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间*/
        int[] temp = new int[numArr.length];
        devide(numArr, 0, numArr.length-1, temp);
    }

    private void devide(int[] numArr, int left, int right, int[] temp) {
        if (left >= right)
            return;

        /**递归拆分系列，从中间将系列一分为二*/
        devide(numArr, left, (left+right)/2, temp);
        devide(numArr, (left+right)/2+1, right, temp);
        /**分到不能再分，开始左右序列排序*/
        merge(numArr, left, right, temp);
    }

    private void merge(int[] numArr, int left, int right, int[] temp) {
        int mid = (left+right)/2;
        int l = left, leftBoundary = mid;
        int r = mid+1, rightBoundary = right;
        int idx = 0;

        while (l <= leftBoundary && r <= rightBoundary) {
            if (numArr[l] < numArr[r])
                temp[idx++] = numArr[l++];
            else
                temp[idx++] = numArr[r++];
        }

        while (l <= leftBoundary)
            temp[idx++] = numArr[l++];

        while (r <= rightBoundary)
            temp[idx++] = numArr[r++];

        /*将排好序的区间[left,right]放回numArr*/
        idx = 0;
        while (left <= right)
            numArr[left++] = temp[idx++];
    }
    
    private void heap(int[] numArr) {
        /*首次调整：从最后一个根节点开始*/
        for (int parentIdx = numArr.length/2-1; parentIdx >= 0; parentIdx--) {
            adjustHeap(numArr, parentIdx, numArr.length);
        }
        /*将堆顶取出放到最后(取最后一个节点和堆顶交换)，再重新从头调整，因为下面都是之前调的好*/
        for (int lastIdx = numArr.length-1; lastIdx > 0; lastIdx--) {
            swap(numArr, 0, lastIdx);
            adjustHeap(numArr, 0, lastIdx);
        }
    }
    
   private void adjustHeap(int[] numArr, int parentIdx, int length) {
       for (int leftChildIdx = 2*parentIdx+1; leftChildIdx < length; leftChildIdx = 2*parentIdx+1) {

           int maxChildIdx = leftChildIdx;
           int rightChildIdx = leftChildIdx+1;
           if (rightChildIdx < length && numArr[rightChildIdx] > numArr[leftChildIdx])
               maxChildIdx = rightChildIdx;

           if (numArr[maxChildIdx] < numArr[parentIdx]) {
               break;
           } else {
               swap(numArr, maxChildIdx, parentIdx);
               /*每次交换后，调整起点（parentIdx）指向最大孩子原来位置*/
               parentIdx = maxChildIdx;
           }
       }
   }

   private void count(int[] numArr, int max) {
       int[] countArr = new int[max];

       for (int i = 0; i < numArr.length; i++) {
           countArr[numArr[i]]++;
       }

       for (int i = 1; i < countArr.length; i++) {
           countArr[i] += countArr[i-1];
       }

       int[] copyArr = Arrays.copyOf(numArr, numArr.length);
       for (int i = 0; i < copyArr.length; i++) {
           int curData = copyArr[i];
           /* int curDataIdx = --countArr[curData];
           可以代替int curDataIdx = countArr[curData]-1; 和 countArr[curData]--;*/
           int curDataIdx = countArr[curData]-1;

           numArr[curDataIdx] = curData;
           countArr[curData]--;
       }
   }

    private void bucket(int[] numArr, int max) {
        int bucketSpan = 100;
        int bucketNum = max%bucketSpan==0 ? max/bucketSpan : max/bucketSpan+1;
        Map<Integer, List<Integer>> allBucket = new HashMap(bucketNum) {{
            for (int i = 0; i < bucketNum; i++) {
                put(i, new ArrayList<Integer>(bucketSpan));
            }
        }};

        for (int i = 0; i < numArr.length; i++) {
            int bucketIdx = numArr[i] / bucketSpan;
            allBucket.get(bucketIdx).add(numArr[i]);
        }

        List<Integer> sortedBucket = new ArrayList<>(numArr.length);
        allBucket.forEach((bucketIdx, bucket) -> {
            Collections.sort(bucket);
            sortedBucket.addAll(bucket);
        });

        for (int i = 0; i < sortedBucket.size(); i++) {
            numArr[i] = sortedBucket.get(i);
        }
    }

    private void base(int[] numArr, int max) {
        for (int digit = 1; digit <= max; digit *= 10) {
            count4Base(numArr, digit);
        }
    }

    private void count4Base(int[] numArr, int digit) {
        int[] countArr = new int[10];

        for (int i = 0; i < numArr.length; i++) {
            countArr[numArr[i]/digit%10]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1];
        }

        int[] copyArr = Arrays.copyOf(numArr, numArr.length);
        for (int i = copyArr.length-1; i >= 0; i--) {
            int curData = copyArr[i];
            int curDataIdx = countArr[curData/digit%10]-1;

            numArr[curDataIdx] = curData;
            countArr[curData/digit%10]--;
        }
    }
}
