public class BackToTextLengthTest {
    public static void main(String[] args) {
        String s = "  ";
        if (s.length() < 2) {
            System.out.println(s);
        }

        int res = 0, start = 0, end = 0;
        int len = s.length();
        for (int i = 1;  i < len; i++) {
            int l = i, r = i; // 默认以当前字符为中心（单心回文）
            // 下面的判断是为了处理"baab"这种回文（双心回文）
            l = s.charAt(l-1) == s.charAt(i) ? l-1 : l; // 有可能左边的字符和当前字符相同
            if (r < len-1) {
                r = s.charAt(r+1) == s.charAt(i) ? r+1 : r;
            }

            while (--l >= 0 && ++r < len) {
                if (s.charAt(l) != s.charAt(r)) {
                    break;
                }
            }

            int maxSubStrlen = r-l-1;
            if (maxSubStrlen > res) {
                // 记录下当前“最长”的回文子串长度，和首尾字符位置
                res = maxSubStrlen;
                start = l + 1;
                end = r - 1;
            }
        }

        System.out.println("["+s.substring(start, end+1)+"]"+s.substring(start, end+1).length());
    }

    public static void main01(String[] args) {
        String s = "babad";
        int res = 0, start = 0, end = 0;
        int len = s.length();
        for (int i = 1;  i < len-1; i++) {
            int idx = i;
            int subStrMaxLen = 1;
            while (idx-1 >= 0 && idx+1 < len) {
                if (s.charAt(idx--) == s.charAt(idx++))
                    subStrMaxLen += 2;
                else
                    break;
            }

            if (subStrMaxLen > res) {
                if (subStrMaxLen == 1) {
                    start = end = idx;
                } else {
                    start = idx - 1;
                    end = idx + 1;
                }

            }
        }

        System.out.println(s.substring(start, end+1));
    }

}
