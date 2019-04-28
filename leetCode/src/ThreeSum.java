import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreeSum {
    public static void main(String[] args) {
//        System.out.println(threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));

        HashSet<Object> set = new HashSet<>();
        set.add(Arrays.asList(1,2,3));
        set.add(Arrays.asList(1,2,3));
        set.add(Arrays.asList(3,2,1));
        System.out.println(set);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resLs = new ArrayList<List<Integer>>(10);

        // 求出两两之和,
        for (int i = 0; i < nums.length; i++)
            for (int j = i+1; j < nums.length; j++) {
                out: for (int k = j+1; k < nums.length; k++)
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tmp = Arrays.asList(nums[i], nums[j], nums[k]);
                        String collect = tmp.stream().sorted().map(e -> e.toString()).collect(Collectors.joining());
                        for (List<Integer> list : resLs) {
                            if (collect.equals(list.stream().sorted().map(e -> e.toString()).collect(Collectors.joining()))) {
                                continue out;
                            }
                        }
                        resLs.add(tmp);
                    }
            }

        return resLs;
    }
}
