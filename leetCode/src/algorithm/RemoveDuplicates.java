package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        int len = new RemoveDuplicates().removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

    /*public int removeDuplicates02(int[] nums) {
        if (nums.length < 2)
            return nums.length;

        HashSet<Integer> set = new HashSet(Arrays.asList(nums));
        Integer[] arr = new Integer[set.size()];
        Object[] objects = set.toArray(arr);
        int i = 0;
        for (int num : (Integer) ) {
            nums[i++] = num;
        }
        return set.size();
    }*/

    /**最原始方案*/
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;

        int result = nums.length;
        for (int i = 0; i < result-1; ) {
            // 如果当前数和下一个数重复，删除下一个数
            if (nums[i] == nums[i+1]) {
                // 从下一个数的位置开始，将所有前移一位
                for (int j = i+1; j < result-1; j++)
                    nums[j] = nums[j+1];
                // 移动完所有数【删除掉了重复的数】，数组长度减1
                // nums.length-- 报错, Line 13: error: cannot assign a value to final variable length
                result--;
            } else {
                i++;
            }
        }

        return result;
    }
}


