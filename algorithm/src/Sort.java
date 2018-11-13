import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class Sort {

    public void printArr(int[] num){
        for (int i = 0; i < num.length; i++) {

            if (i!=0 && i%10==0)
                System.out.println();

            System.out.print(num[i] + "、");
        }
        System.out.println();
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

    public void swap(int[] arr, int a, int b) {
        if (a == b) return;
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    @Test
    public void testSort(){

        int[] num = initArrPositive(20, 20);
//        int[] num = new int[]{2,5,3,4,8,1,2,7};
        System.out.println("before sort: ");
        printArr(num);

//        testBubble(num);
//        testSelect(num);
//        testInsert(num);
//        testShell(num);
//        testShell02(num);
//        testQuick(num, 0, 7);
//        testMerge(num);
//        testHeap(num = new int[]{2,5,3,4,1,2,7});
//        testCount(num = new int[]{1,2,4,3,4,0,5,4,3,0}, 6);
//        testBucket(num, 20);
        testRadix(num = new int[]{1,32,624,36,124,10,35,344,3789,5460}, 5460);
        System.out.println("after sort: ");
        printArr(num);
    }

    //冒：真正的冒泡，有两两对比
    public void testBubble(int[] num){
        int total = num.length;

        for (int i = 0; i < total-1; i++) {
            for (int j = 0; j < total-i-1; j++) {
                if (num[j] > num[j+1]){
                    swap(num, j, j+1);
                }
            }
        }
    }

    //择
    public void testSelect(int[] num){
        int total = num.length;
        /**
         * 数组中从头到尾取每一个数，和它之后的每一个数比较，
         * 如果后面的数小于当前数，则将后面的数和当前数调换位置
         */
        for (int i = 0; i < total-1; i++) {
            for (int j = i+1; j < total; j++) {
                if (num[j] < num[i]){
                    swap(num, i, j);
                }
            }
        }
    }

    //入
    public void testInsert(int[] num){
        int total = num.length;

        for (int i = 1; i < total; i++) {
            int cur = num[i];
            int preIdx = i-1;
            /**
             * 将当前要排序的数，逐一和已经排序的数比较，
             * 若当前数大于已排序的数，将已排序的数后移一位
             * 直到数组边界 或 已经排序的数没有比当前数小，将当前数插入到移出来的空位
             */
            while (preIdx>=0 && cur<num[preIdx]){
                num[preIdx + 1] = num[preIdx];
                preIdx--;//当前数比已排序的数小，还可能比更前面的数（已排序）小，所以将要比较的位置前移一位
            }
            num[preIdx+1] = cur;
        }
    }

    //希
    public void testShell(int[] num){
        int total = num.length;

        for (int gap=total/2; gap>0; gap>>=1){
            for (int i = gap; i < total; i++) {

                int cur = num[i];
                int preIdx = i-gap;
                while (preIdx>=0 && cur<num[preIdx]){
                    num[preIdx+gap] = num[preIdx];//后移法
                    preIdx -= gap;//当前数比已排序的数小，还可能比更前面的数（已排序）小，所以将要比较的位置前移gap位
                }
                num[preIdx+gap] = cur;
            }
        }
    }

    public void testShell02(int[] num){
        int total = num.length;

        for(int gap = total/2; gap > 0; gap >>= 1){
            for (int i = gap; i < total; i++) {

                int cur = i;
                while(cur-gap>=0 && num[cur]<num[cur-gap]){
                    swap(num, cur, cur-gap);//交换法
                    cur -= gap;//当前数比已排序的数小，还可能比更前面的数（已排序）小，所以将要比较的位置前移gap位
                }
            }
        }
    }

    //快
    public void testQuick(int[] num, int left, int right){

        if (left > right){
            return ;
        }

        int base = num[left];
        int l = left;
        int r = right;

        while (l != r){

            //两个哨兵没碰面之前，右哨兵一直找小于基准的值
            while (num[r]>=base && l<r)
                r --;

            //两个哨兵没碰面之前，左哨兵一直找大于基准的值
            while (num[l]<=base && l<r)
                l ++;

            //两个哨兵没碰面之前，都找到了目标，交换
            if (l < r)
                swap(num, l, r);
        }

        //基准归位
        swap(num, left, l);

        //分而治之
        testQuick(num, left, l-1);
        testQuick(num, l+1, right);
    }

    //归
    public void testMerge(int[] arr){
        int[] temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        devide(arr,0,arr.length-1, temp);
    }

    private void devide(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left+right)/2;
            devide(arr, left, mid, temp);//左序列归并排序
            devide(arr, mid+1, right, temp);//右序列归并排序
            combine(arr, left, right, mid, temp);//合并左右序列
        }
    }

    private void combine(int[] arr, int left, int right, int mid, int[] temp){
        int l = left;//左系列指针
        int r = mid+1;//右系列指针
        int t = 0;//临时队列指针

        while(l<=mid && r<=right){
            if (arr[l] < arr[r])
                temp[t++] = arr[l++];
            else
                temp[t++] = arr[r++];
        }

        while(l<=mid)//将左序列剩余元素填充到temp
            temp[t++] = arr[l++];

        while(r<=right)//将右序列剩余元素填充到temp
            temp[t++] = arr[r++];


        t = 0;//将temp中的元素全部拷贝到原数组中
        while(left <= right)
            arr[left++] = temp[t++];

//        arr = temp;
    }

    //堆 有问题
    public void testHeap(int[] arr) {
        int lastIdx = arr.length - 1;
        for (int i = lastIdx/2-1; i >= 0; i--) { //堆构造
            heapAdjust(arr, i, lastIdx);
        }
        while (lastIdx >= 0) {
            swap(arr, 0, lastIdx--); //将堆顶元素与尾节点交换后，长度减1，尾元素最大
            heapAdjust(arr, 0, lastIdx); //再次对堆进行调整
        }
    }

    private void heapAdjust(int[] arr, int fatherIdx, int len) {
        int left, right, sonIdx; //sonIdx用来指向大的子节点
        while ((left = 2*fatherIdx+1) <= len) { //当前父节点有(左)子节点的情况
            right = left + 1; //右节点
            sonIdx = left;
            if (sonIdx<len && arr[left]<arr[right]) { //右节点大于左节点
                sonIdx = right;
            }
            if (arr[fatherIdx] < arr[sonIdx]) { //最大子节点大于父节点
                swap(arr, fatherIdx, sonIdx);
            } else { //父节点比孩子节点都大，跳出循环
                break;
            }
            fatherIdx = sonIdx;
        }
    }

    //计

    /**
     * @param arr 不能有负数、小数
     * @param max 最大值不宜太大
     */
    public void testCount(int[] arr, int max) {
        int total = arr.length;
        int[] originArr = Arrays.copyOf(arr, total);
        int[] countArr = new int[max + 1]; //统计所有元素出现个数的数组，max+1长度是为了统计0
        /**
         * 此时的countArr表示： 下标【元素值】 和  值【出现次数】  的关系
         */
        for (int i = 0; i < total; i++) {
            countArr[originArr[i]] ++; //统计原始数组各个元素出现的次数【出现一次，在下标等于元素值的位置插一个小旗】
        }
        /**
         * 此时的countArr表示： 下标【元素值】 和  值【最终排位】  的关系
         */
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1]; //累计当前位置前面【包括当前位置的小旗】，总共的小旗数量，这样就能算出当前元素的最终排位
        }
        for (int i = total-1; i >= 0; i--) {
            int curVal = originArr[i];
            arr[countArr[curVal]-1] = curVal; //根据统计结果，找到当前元素应排的位置【最终排位全部往前移一位】
            countArr[curVal] --; //将当前元素的统计结果自减【拔掉一个小旗】，便于对相同值的元素排序
        }
    }

    //桶
    public void testBucket(int[] arr, int max) {

        int bucketNum = max%10==0 ? (max/10) : (max/10+1); //统计需要的桶数量【每个桶的存放跨度是10】
        int[][] allBucket = new int[bucketNum][10*2]; //每个桶存放跨度是10，但存量不一定是10
        //记录各个桶实际大小
        Map<Integer, Integer> allBucketSize = new HashMap(bucketNum){{
            for (int i = 0; i < bucketNum; i++) {
                put(i, 0);
            }
        }};

        //将要排序的数据，散列到各个桶
        for (int i = 0; i < arr.length; i++) {
            int bucketIdx = arr[i]/10; //计算当前数值存放在哪个桶
            Integer curBucketSize = allBucketSize.get(bucketIdx); //当前桶的实际大小, 即当前数值存放桶（数组）的哪个位置
            allBucket[bucketIdx][curBucketSize] = arr[i];
            allBucketSize.replace(bucketIdx, curBucketSize+1); //当前桶实际大小+1
        }
        System.out.println("原始数据散列到各个桶: " +  Arrays.deepToString(allBucket));

        //将每个桶的0去掉
        int[][] allBucketWithout_0 = new int[bucketNum][];
        for (int i = 0; i < bucketNum; i++) {
            allBucketWithout_0[i] = new int[allBucketSize.get(i)]; //根据每个桶实际长度，创建不定长二维数组

            int count = 0; //每个非0元素存放在allBucketWithout_0中的位置
            for (int j = 0; j < allBucket[i].length; j++) {
                if (0 != allBucket[i][j])
                    allBucketWithout_0[i][count++] = allBucket[i][j];
            }
        }
        allBucket = allBucketWithout_0;

        //每个桶使用计数排序
        for (int i = 0; i < bucketNum; i++) {
            testCount(allBucket[i], (i+1)*10);
        }
        System.out.println("各个桶排序之后: " +  Arrays.deepToString(allBucket));


        int totalLen = allBucketSize.values().stream().mapToInt(Integer::intValue).sum(); //计算每个桶实际长度总和
        int offset = 0;
        if (totalLen < arr.length) { //如果每个桶实际长度总和 小于 原始数组总长，则合并时多留几个0
            offset = arr.length - totalLen;
        }
        //多个桶合并
        int[] combineArr = new int[arr.length];
        int skip = offset;
        for (int i = 0; i < bucketNum; i++) {
            System.arraycopy(allBucket[i], 0, combineArr, skip, allBucket[i].length);
            skip += allBucket[i].length;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = combineArr[i];
        }
    }

    //基数
    public void testRadix(int[] arr, int max) {
        for (int exp = 1; max/exp > 0; exp*=10) {
            testCountWithExp(arr, exp);
        }
    }

    public void testCountWithExp(int[] arr, int exp) {
        int total = arr.length;
        int[] originArr = Arrays.copyOf(arr, total);
        int[] countArr = new int[10]; //统计所有元素的位数值【个，十，百】，出现个数的数组
        /**
         * 此时的countArr表示： 下标【元素的位数值】 和  值【出现次数】  的关系
         */
        for (int i = 0; i < total; i++) {
            countArr[(originArr[i]/exp)%10]++; //统计原始数组各个元素的位数值出现的次数【出现一次，在下标等于元素的的位数值的位置插一个小旗】
        }
        /**
         * 此时的countArr表示： 下标【元素的位数值】 和  值【最终排位】 的关系
         */
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1]; //累计当前位置前面【包括当前位置的小旗】，总共的小旗数量，这样就能算出当前元素的位数值的最终排位
        }
        for (int i = total-1; i >= 0; i--) {
            int curVal = originArr[i];
            int curValRadix = (curVal/exp)%10;
            arr[countArr[curValRadix] - 1] = curVal; //根据统计结果，找到当前元素的位数值应排的位置【最终排位全部往前移一位】，也即元素的排位
            countArr[curValRadix] --; //将当前元素的位数值的统计结果自减【拔掉一个小旗】，便于对相同值的元素位数值排序
        }

    }

    @Test
    public void testDirectSwap() {
        int a=3, b=5;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a="+a +","+ "b="+b);

        a>>=1;
        System.out.println(a);
    }

    @Test
    public void testDivision() {
        System.out.println(99/100);
    }

    @Test
    public void testArr() {
        int[][] arr2 = new int[3][4];
        for (int i = 0; i < arr2.length; i++) {
//            System.out.println("=====================");
            for (int j = 0; j < arr2[i].length; j++) {
//                System.out.println(i+""+j);
            }
        }
    }

}
