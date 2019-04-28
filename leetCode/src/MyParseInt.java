public class MyParseInt {
    public static void main(String[] args) {
        System.out.println(myAtoi("   -42"));
    }
    public static int myAtoi(String str) {
        str = str.trim();
        int sign = 1;
        int res = 0;

        if (str.length() < 1)
            return 0;

        char[] chArr = str.toCharArray();
        // 字符'0'的ASCII是：48
        if (Character.isDigit(chArr[0])) {
            res = chArr[0] - 48;
        } else if (chArr[0] == '-' || chArr[0] == '+') {
            sign = chArr[0] == '-' ? -1 : 1;
        } else {
            return 0;
        }

        int curDigit;
        for (int i = 1; i < chArr.length; i++) {
            if (!Character.isDigit(chArr[i]))
                break;

            /** 2^31-1=2147483647,-2^31=-2147483648*/
            curDigit = chArr[i] - 48;
            // 用于比较的res, 要带上符号
            if (sign*res > Integer.MAX_VALUE/10 || (sign*res == Integer.MAX_VALUE/10 && curDigit > 7))
                return Integer.MAX_VALUE;
            if (sign*res < Integer.MIN_VALUE/10 || (sign*res == Integer.MIN_VALUE/10 && curDigit > 8))
                return Integer.MIN_VALUE;

            res = res * 10 + curDigit;
        }

        return sign * res;
    }
}
