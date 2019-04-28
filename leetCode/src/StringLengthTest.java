import java.util.*;
import java.util.stream.Stream;

public class StringLengthTest {
    public static void main(String[] args) {
        String s = "vdedfaev";

        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        System.out.println(ans);
    }

    public static void main02(String[] args) {
        String s = "dvdf";
        int len = s.length();
        int res = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>(len);
        while (i < len && j < len) {
            if (set.contains(s.charAt(j))) {
                int size = set.size();
                res = size > res ? size : res;
                j = i++; //换窗口起点
                set.clear();
            } else {
                set.add(s.charAt(j));
                j++; //往窗口右侧滑动
            }
        }

        int size = set.size();
        res = size > res ? size : res;
        System.out.println(res);
    }

    public static void main01(String[] args) {
        String s = "abcabcbb";
//        s = "bbbbb";
        s = "pwwkew";
//        s = " ";
//        s = "";
//        s = "au";
//        s = "dvdf";

        char[] charArr = s.toCharArray();
        if (charArr.length < 2) {
            System.out.println(charArr.length);
            return;
        }

        int maxSubStrLen = 0;
        int start = 0;
        int i = 1;

        for (; i < charArr.length; i++) {

            int idx = s.substring(start, i).indexOf(charArr[i]+"");
            if (idx != -1) { //出现重复字符
                maxSubStrLen = (i-start) > maxSubStrLen ? (i-start) : maxSubStrLen;
                i = idx;
                start = idx;
            }
        }

        //有可能整个字符串走完，都没有出现重复
        maxSubStrLen = (i-start) > maxSubStrLen ? (i-start) : maxSubStrLen;

        System.out.println(maxSubStrLen);
    }

    /*char[] charArr = s.toCharArray();
        if (charArr.length < 2) {
            return charArr.length;
        }

        int maxSubStrLen = 0;
        int start = 0;
        int subStrLen = 1;

        for (int i = 1; i < charArr.length; i++) {

            //没有出现重复字符，子字符串长度增加
            int idx = s.substring(start, i).indexOf(charArr[i]+"");
            if (idx != -1) {
                subStrLen++;
            } else {
                //出现重复字符
                maxSubStrLen = subStrLen > maxSubStrLen ? subStrLen : maxSubStrLen;
                subStrLen = i - idx;
                start = idx+1;
            }
        }

        //有可能整个字符串走完，都没有出现重复
        maxSubStrLen = subStrLen > maxSubStrLen ? subStrLen : maxSubStrLen;

        return maxSubStrLen;*/
}

