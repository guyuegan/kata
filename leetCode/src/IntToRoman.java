import java.util.HashMap;
import java.util.Map;

public class IntToRoman {

    public static void main(String[] args) {
        int num = 9;
        Map map1 = new HashMap<Integer, Character>(2) {{put(0, 'I'); put(1, 'V');}};
        Map map10 = new HashMap<Integer, Character>(2) {{put(0, 'X'); put(1, 'L');}};
        Map map100 = new HashMap<Integer, Character>(2) {{put(0, 'C'); put(1, 'D');}};
        Map map1000 = new HashMap<Integer, Character>(1) {{put(0, 'M');}};

        Map<Integer, Map> chMap = new HashMap<Integer, Map>(4);
        chMap.put(1, map1);
        chMap.put(10, map10);
        chMap.put(100, map100);
        chMap.put(1000, map1000);

        int lastNum = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; num >= i; i *= 10) {
            lastNum = num/i%10;
            if (lastNum == 0) {
                continue;
            }
            if (lastNum == 4) {
                sb.append(chMap.get(i).get(1)).append(chMap.get(i).get(0));
                continue;
            }
            if (lastNum == 9) {
                sb.append(chMap.get(i*10).get(0)).append(chMap.get(i).get(0));
                continue;
            }
            if (lastNum == 5) {
                sb.append(chMap.get(i).get(1));
                continue;
            }
            // 1--3 【重复多次】
            for (int j = 1; j <= lastNum%5; j++) {
                sb.append(chMap.get(i).get(0));
            }
            // 5--8 【如果大于5，还要在重复前面加上一个字符】
            if (lastNum > 5) {
                sb.append(chMap.get(i).get(1));
            }
        }

        System.out.println(sb.reverse().toString());
    }

    public static void main01(String[] args) {
        /*StringBuilder sb = new StringBuilder("1");
        sb.insert(0, '2').insert(0, '3');
        sb.insert(0, '4').insert(0, '5');
        System.out.println(sb);*/

        int num = 1;

        char[][] ch = {{'I', 'V'}, {'X', 'L'}, {'C', 'D'}, {'M'}};
        int lastNum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; num >= i; i *= 10) {
            lastNum = num/i%10;
            int idx = Double.valueOf(Math.log10(i)).intValue();
            if (lastNum == 0) {
                continue;
            }
            if (lastNum == 4) {
                sb.insert(0, ch[idx]);
                continue;
            }
            if (lastNum == 9) {
                sb.insert(0, ch[idx+1][0]).insert(0, ch[idx][0]);
                continue;
            }
            if (lastNum == 5) {
                sb.insert(0, ch[idx][1]);
                continue;
            }
            // 1--3
            for (int j = 1; j <= lastNum%5; j++) {
                sb.insert(0, ch[idx][0]);
            }
            // 5--8
            if (lastNum > 5) {
                sb.insert(0, ch[idx][1]);
            }
        }

        System.out.println(sb.toString());
    }
}














