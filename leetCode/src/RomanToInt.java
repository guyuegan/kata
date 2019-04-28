import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

    public static void main(String[] args) {
        System.out.println(romanToInt("IV"));
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> chIntMap = new HashMap(7);
        chIntMap.put('I', 1);
        chIntMap.put('V', 5);
        chIntMap.put('X', 10);
        chIntMap.put('L', 50);
        chIntMap.put('C', 100);
        chIntMap.put('D', 500);
        chIntMap.put('M', 1000);

        int prevInt = 0;
        int curInt = 0;
        int res = 0;
        for (char ch : s.toCharArray()) {
            curInt = chIntMap.get(ch);
            if (curInt > prevInt) {
                curInt -= 2*prevInt;
            }
            res += curInt;
            prevInt = curInt;
        }

        return res;
    }
}

