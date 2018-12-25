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
//        shell(numArr);
//        quick(numArr, 0, numArr.length-1);
//        devideAndMerge(numArr);
        heap(numArr);
        System.out.println("after sort: \n" + Arrays.toString(numArr));
    }

    //冒
    private void bubble(int[] numArr) {
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
    private void choose(int[] numArr) {
        for (int i = 0; i < numArr.length; i++) {
            for (int j = i+1; j < numArr.length; j++) {
                if (numArr[i] > numArr[j])
                    swap(numArr, i, j);
            }
        }
    }

    //入
    private void insert(int[] numArr) {
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
    private void shell(int[] numArr) {
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

    //快
    private void quick(int[] numArr, int left, int right) {
        if (left >= right)
            return;

        int baseIdx = left, l = left, r = right;

        while (l != r) {
            /**左边小机器人l, 只要没有碰到小机器人r并且没找到比标准值小的数, 就一直往左找*/
            while (l != r && numArr[r] >= numArr[baseIdx])
                r--;
            /**同上*/
            while (l != r && numArr[l] <= numArr[baseIdx])
                l++;

            if (l != r)
                swap(numArr, l, r);
        }
        /**此时l、r在标准值归位的位置汇合*/
        swap(numArr, baseIdx, r);

        /**标准值左、右边子序列继续快排*/
        quick(numArr, left, r-1);
        quick(numArr, r+1, right);
    }

    //归
    private void devideAndMerge(int[] numArr) {
        /**在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间*/
        int[] temp = new int[numArr.length];
        devide(numArr, 0, temp.length-1, temp);
    }

    private void devide(int[] numArr, int left, int right, int[] temp) {
        if (left >= right)
            return;

        /**递归拆分系列，从中间将系列一分为二*/
        devide(numArr, left, (right+left)/2, temp);
        devide(numArr, (right+left)/2+1, right, temp);
        /**分到不能再分，开始左右序列排序*/
        merge(numArr, left, right, temp);
    }

    /**将[left, right]区间所有元素排序, 实际看做[left, mid] 和 [mid+1, right]两个系列*/
    private void merge(int[] numArr, int left, int right, int[] temp) {
        int mid = (left+right)/2;
        int l = left, leftBoundary = mid;
        int r = mid+1, rightBoundary = right;
        int idx = 0;

        /**只有排完左序列 或 右序列才能停止*/
        while (l <= leftBoundary && r <= rightBoundary) {
            if (numArr[l] <= numArr[r])
                temp[idx++] = numArr[l++];
            else
                temp[idx++] = numArr[r++];
        }

        /**如果左序列没有排完*/
        while (l <= leftBoundary)
            temp[idx++] = numArr[l++];

        /**如果右序列没有排完*/
        while (r <= rightBoundary)
            temp[idx++] = numArr[r++];

        /**左右序列都排完*/
        idx = 0;
        while(left <= right)
            numArr[left++] = temp[idx++];
    }

    //堆
    private void heap(int[] numArr) {
        /**首次从下往上调整*/
        for (int parentIdx = numArr.length/2-1; parentIdx >= 0; parentIdx--) {
            adjustHeap(numArr, parentIdx, numArr.length);
        }
        /**将构造成大顶堆之后的根节点，放到最后*/
        for (int lastIdx = numArr.length-1; lastIdx > 0; lastIdx--) {
            swap(numArr, 0, lastIdx);
            /**发生交换后，重新调整堆结构，由于之前底下的子树都是满足大顶堆，所以从上往下调整*/
            adjustHeap(numArr, 0, lastIdx);
        }
    }

    /**
     * @param parentIdx 选举老大的位置
     * @param length    选举老大的范围
     */
    private void adjustHeap(int[] numArr, int parentIdx, int length) {
        for (int leftChildIdx = parentIdx*2+1; leftChildIdx < length; leftChildIdx = parentIdx*2+1) {
            int maxChildIdx = leftChildIdx;

            int rightChildIdx = leftChildIdx+1;
            if (rightChildIdx < length && numArr[rightChildIdx] > numArr[maxChildIdx])
                maxChildIdx = rightChildIdx;

            /**老大被换下来之后，暂时坐在之前最大儿子的位置上，换下来的老大，还能不能在这个位置当老大，比一比才知道
             * 接下来比的是交换位置（之前最大儿子的位置）的子节点*/
            if (numArr[maxChildIdx] > numArr[parentIdx]) {
                swap(numArr, parentIdx, maxChildIdx);
                parentIdx = maxChildIdx; /**交换后，最大儿子的位置，已经变成选举老大的位置*/
            } else {
                break;
            }
        }
    }
}
