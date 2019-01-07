import org.junit.Test;

import java.util.*;

public class SortPractice4 {
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
            /*大值下沉*/
            /*for (int j = 0; j < numArr.length-i-1; j++) {
                if (numArr[j] > numArr[j+1])
                    swap(numArr, j, j+1);
            }*/

            /*小值上升*/
            for (int j = numArr.length-1; j > i ; j--) {
                if (numArr[j] < numArr[j-1])
                    swap(numArr, j, j-1);
            }
        }
    }

    private void choose(int[] numArr) {
        for (int i = 0; i < numArr.length-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < numArr.length; j++) {
                if (numArr[j] < numArr[minIdx])
                    minIdx = j;
            }

            swap(numArr, i, minIdx);
        }
    }

    private void insert(int[] numArr) {
        for (int i = 1; i < numArr.length; i++) {
            int curData = numArr[i];
            int prevIdx = i-1;

            while (prevIdx >= 0 && curData < numArr[prevIdx]) {
                numArr[prevIdx + 1] = numArr[prevIdx];
                prevIdx--;
            }

            numArr[prevIdx+1] = curData;
        }
    }

    private void shell(int[] numArr) {
        for (int gap = numArr.length >> 1; gap >= 1; gap >>= 1) {

            for (int i = 0; i < gap; i++) {
                for (int j = gap+i; j < numArr.length; j+=gap) {
                    int curData = numArr[j];
                    int prevIdx = j-gap;

                    while (prevIdx >= 0 && curData < numArr[prevIdx]) {
                        numArr[prevIdx+gap] = numArr[prevIdx];
                        prevIdx -= gap;
                    }
                    numArr[prevIdx+gap] = curData;
                }
            }

        }
    }

    private void quick(int[] numArr, int left, int right) {
        if (left >= right)
            return;

        int b=left, l=left, r=right;

        while ( l != r) {
            while (l != r && numArr[r] > numArr[b])
                r--;

            while (l != r && numArr[l] < numArr[b])
                l++;

            if (l != r)
                swap(numArr, l, r);
        }

        swap(numArr, b, l);

        quick(numArr, left, l);
        quick(numArr, l+1, right);
    }

    public void devideAndMerge(int[] numArr) {
        int[] temp = new int[numArr.length];
        devide(numArr, 0, numArr.length-1, temp);
    }

    private void devide(int[] numArr, int left, int right, int[] temp) {
        if (left >= right)
            return;

        devide(numArr, left, (left+right)/2, temp);
        devide(numArr, (left+right)/2+1, right, temp);

        merge(numArr, left, right, temp);
    }

    private void merge(int[] numArr, int left, int right, int[] temp) {
        int l = left, m = (left+right)/2, r = m+1;
        int lb = m, rb = right, tmpIdx = 0;

        while (l <= lb && r <= rb) {
            if (numArr[l] < numArr[r])
                temp[tmpIdx++] = numArr[l++];
            else
                temp[tmpIdx++] = numArr[r++];
        }

        while (l <= lb)
            temp[tmpIdx++] = numArr[l++];

        while (r <= rb)
            temp[tmpIdx++] = numArr[r++];

        tmpIdx = 0;
        while (left <= right)
            numArr[left++] = temp[tmpIdx++];
    }

    private void heap(int[] numArr) {
        for (int parentIdx = numArr.length/2-1; parentIdx >= 0; parentIdx--) {
            adjustHeap(numArr, parentIdx, numArr.length);
        }

        for (int lastIdx = numArr.length-1; lastIdx >= 0; lastIdx--) {
            swap(numArr, 0, lastIdx);
            adjustHeap(numArr, 0, lastIdx);
        }
    }

    private void adjustHeap(int[] numArr, int parentIdx, int length) {
        for (int leftChildIdx = parentIdx*2+1; leftChildIdx < length; leftChildIdx = parentIdx*2+1) {
            int maxChildIdx = leftChildIdx;
            int rightChildIdx = leftChildIdx+1;
            if (rightChildIdx < length && numArr[rightChildIdx] > numArr[maxChildIdx])
                maxChildIdx = rightChildIdx;

            if (numArr[maxChildIdx] > numArr[parentIdx]) {
                swap(numArr, maxChildIdx, parentIdx);
                parentIdx = maxChildIdx;
            } else {
                break;
            }
        }
    }

    private void count(int[] numArr, int max) {
        int[] countArr = new int[max + 1];

        for (int i = 0; i < numArr.length; i++) {
            countArr[numArr[i]]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1];
        }

        int[] copyArr = Arrays.copyOf(numArr, numArr.length);
        for (int i = 0; i < copyArr.length; i++) {
            int curData = copyArr[i];
            int curDataIdx = countArr[curData]-1;
            numArr[curDataIdx] = curData;

            countArr[curData]--;
        }
    }

    private void bucket(int[] numArr, int max) {
        int bucketSpan = 10;
        int bucketNum = max%bucketSpan==0 ? max/bucketSpan : max/bucketSpan+1;

        Map<Integer, List<Integer>> allBucket = new HashMap(bucketNum) {{
            for (int i = 0; i < bucketNum; i++) {
                put(i, new ArrayList<Integer>(bucketSpan / 2));
            }
        }};

        for (int i = 0; i < numArr.length; i++) {
            int bucketIdx = numArr[i]/bucketSpan;
            allBucket.get(bucketIdx).add(numArr[i]);
        }

        ArrayList<Integer> sortedList = new ArrayList<>(numArr.length);
        allBucket.forEach((bucketIdx, bucket) -> {
            Collections.sort(bucket);
            sortedList.addAll(bucket);
        });

        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = sortedList.get(i);
        }
    }

    private void base(int[] numArr, int max) {
        for (int digit = 1; digit <= max; digit *= 10) {
            countForbase(numArr, digit);
        }
    }

    private void countForbase(int[] numArr, int digit) {
        int[] countArr = new int[10];

        for (int i = 0; i < numArr.length; i++) {
            int curDataDigit = numArr[i]/digit%10;
            countArr[curDataDigit]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1];
        }

        int[] copyArr = Arrays.copyOf(numArr, numArr.length);
        for (int i = copyArr.length-1; i >= 0; i--) {
            int curDataDigit = copyArr[i]/digit%10;
            int curDataIdx = countArr[curDataDigit]-1;
            numArr[curDataIdx] = copyArr[i];

            countArr[curDataDigit]--;
        }
    }
}
