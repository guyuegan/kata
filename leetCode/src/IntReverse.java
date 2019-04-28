public class IntReverse {
    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        StringBuilder sb = new StringBuilder(Integer.toString(Math.abs(x))).reverse();
        if (x < 0) {
            sb = new StringBuilder("-").append(sb);
        }

        int res;
        try{
            res = Integer.parseInt(sb.toString());
        } catch(Exception e) {
            return 0;
        }

        return res;
    }


}
