import java.util.*;

public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);
    }
    /*暴力解法*/
    /*public static List<String> generateParenthesis(int n) {
        Set<String> set = new HashSet<>(Double.valueOf(Math.pow(2, n)).intValue());
        return new ArrayList<>(generateParenthesis(n, set));
    }


    public static Set<String> generateParenthesis(int n, Set<String> resSet) {
        if (0 == n) return new HashSet<>(0);

        if (1 == n) return new HashSet<>(Arrays.asList("()"));

        *//*for (String s : generateParenthesis(--n)) {
            tempSet.add("()"+s);
            tempSet.add(s+"()");
            tempSet.add("("+s+")");
        }*//*

        for (String s : generateParenthesis(--n)) {
            for (int i = 0; i < s.length(); i++)
                resSet.add(s.substring(0,i)+"()"+s.substring(i));
            resSet.add("()"+s);
        }

        return resSet;
    }*/

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, "", 0, 0, n);
        return res;
    }
    //l统计“(”的个数，r统计“)”的个数
    public static void generate(List<String> res , String curParenthesis, int l, int r, int n){
        if(2*n == curParenthesis.length())  {
            res.add(curParenthesis);
            return;
        }

        if(l < n) generate(res, curParenthesis+"(", l+1, r, n);
        if(r < l) generate(res, curParenthesis+")", l, r+1, n);
    }
}

