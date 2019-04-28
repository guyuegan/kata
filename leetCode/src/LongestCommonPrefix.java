import java.util.*;

public class LongestCommonPrefix {
    public static void main(String[] args) {

        Set<Integer> integers = new HashSet<>(Arrays.asList(1, 2, 2, 3));
        System.out.println(integers);


//        String[] strs = {"a","b","c"};
//        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length-1);
    }

    private static String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) return strs[l];
        int mid = (l + r) / 2;
        String lcpLeft = longestCommonPrefix(strs, l, mid);
        String lcpRight = longestCommonPrefix(strs, mid+1, r);
        return commonPrefix(lcpLeft, lcpRight);
    }

    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }

    public static String longestCommonPrefix02(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static String longestCommonPrefix01(String[] strs) {
        if (strs.length < 1) return "";

        // 取出最短字符串长度
        int shortest = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < shortest)
                shortest = strs[i].length();
        }

        //
        char ch;
        int i;
        out : for (i = 0; i < shortest; i++) {
            ch = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++)
                if (strs[j].charAt(i) != ch) break out;
        }

        if (i <= 0)
            return "";

        return strs[0].substring(0, i);
    }
}
