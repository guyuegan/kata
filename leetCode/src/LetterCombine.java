import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombine {
    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }

    private static Map<Character, String> numChMap = new HashMap<>(8);
    static {
        numChMap.put('2', "abc");
        numChMap.put('3', "def");
        numChMap.put('4', "ghi");
        numChMap.put('5', "jkl");
        numChMap.put('6', "mno");
        numChMap.put('7', "pqrs");
        numChMap.put('8', "tuv");
        numChMap.put('9', "wxyz");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        if (digits.length() < 1)
            return list;

        String firstNumCh = numChMap.get(digits.charAt(0));
        for (int i = 0; i < firstNumCh.length(); i++) {
            StringBuilder sb = new StringBuilder(firstNumCh.charAt(i)+"");
            dfs(1, digits, sb, list);
        }

        return list;
    }

    private static void dfs (int idx, String digits, StringBuilder sb, List<String> list) {
        // 南墙
        if (sb.length() == digits.length()) {
            list.add(sb.toString());
            sb.deleteCharAt(sb.length()-1);
            return;
        }

        String tmp = numChMap.get(digits.charAt(idx));
        for (int i = 0; i < tmp.length(); i++) {
            sb.append(tmp.charAt(i));
            dfs(idx+1, digits, sb, list);
        }
        sb.deleteCharAt(sb.length()-1);
    }
}
