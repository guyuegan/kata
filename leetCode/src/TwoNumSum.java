import java.util.HashMap;
import java.util.Map;

public class TwoNumSum {
    public static void main(String[] args) {
        /*int [] nums = {3, 3};
        int target = 6;

        Map<Integer, Integer> map = new HashMap<>(nums.length);

        *//**
         我们可以一次完成。在进行迭代并将元素插入到表中的同时，
         我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素
         *//*
        for(int i=0; i<nums.length; i++) {

            int diff = target - nums[i];
            Integer idx = map.get(diff);
            if(idx != null && idx != i)
                System.out.println(""+i+","+idx);

//             先不要把“自己”加进去，否则nums=[3,3], target=6时会出错
            map.put(nums[i], i);
        }


        throw new IllegalArgumentException("No two sum solution");*/


    }
}
