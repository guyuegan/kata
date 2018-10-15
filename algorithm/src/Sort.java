import org.junit.Test;

import java.util.Random;

public class Sort {

    public void printArr(int[] num){
        for (int i = 0; i < num.length; i++) {

            if (i!=0 && i%10==0)
                System.out.println();

            System.out.print(num[i] + "、");
        }
        System.out.println();
    }

    public int[] initArr(int length, int max){
        Random random = new Random();
        int[] num = new int[length];
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(max) - max/2; // -max/2 -- max/2
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

        int[] num = initArr(10, 10);
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
        testHeap(num = new int[]{2,5,3,4,1,2,7});

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

}
