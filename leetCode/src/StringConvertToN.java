public class StringConvertToN {
    public static void main(String[] args) {

        System.out.println(Math.floor(Math.log10(9))+1);
        byte[] arr = new byte[Double.valueOf(Math.floor(Math.log10(9))+1).intValue()];

        /*String res = convert("ABC", 1);
        System.out.println(res);*/
    }

    public static String convert(String s, int numRows) {

        if (s.length() <= 2 || numRows==1)
            return s;

        StringBuilder[] sb = new StringBuilder[numRows];
        int len = s.length();
        int i = 0;
        int curRow = 0;

        boolean isAddRow = true;

        while (i < len){
            // 行递增
            if (isAddRow)  {
                if (curRow != numRows-1) {
                    sb[curRow++].append(s.charAt(i++));
                } else { // 到最后一行了
                    isAddRow = false; //调整方向，开始行递减
                }
            }
            // 行递减
            else {
                if (curRow != 0) {
                    sb[curRow--].append(s.charAt(i++));
                } else {// 到第一行了
                    isAddRow = true;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int n = 0; n < sb.length; n++) {
            res.append(sb[n]);
        }

        return res.toString();
    }

}
