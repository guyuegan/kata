import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSubExp {

    // &nbsp;{2,} 只能匹配 &nbsp;;;;;; 【因为{2,}只作用于前一个元素】
    private static Pattern pattern = Pattern.compile("&nbsp;{2,}");
    // 【子表达式限定作用】(&nbsp;){2,} 可以匹配 &nbsp;&nbsp; ()括起来的就是一个子表达式
    private static Pattern pattern2 = Pattern.compile("(&nbsp;){2,}");

    // 粗略匹配ip
    private static Pattern pattern3 = Pattern.compile("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}");
    // 【子表达式简化作用】
    private static Pattern pattern4 = Pattern.compile("(\\d{1,3}.){3}\\d{1,3}");

    // "19|20\\d{2}"只会匹配出 19 或 20XX, 因为|把位于它左边和右边的两个部分都作为一个整体看待，
    // 即：(19)|(20\\d{2}), 所以要匹配出正确的出生年份应该用"(19|20)\\d{2}"
    private static Pattern pattern5 = Pattern.compile("19|20\\d{2}");
    // 【子表达式去提高优先级作用】
    private static Pattern pattern6 = Pattern.compile("(19|20)\\d{2}");

    // 精确匹配ip

    /**
     * 上面用"(\\d{1,3}.){3}\\d{1,3}"粗略匹配ip是有问题的，这个模式会匹配出不合法的ip, 例：666.77.8.999
     * 合法的ip是由4个字节组成（对应逗号隔开的4组数字），一个字节的所表示范围是0~255，即ip中的每组数字都是0~255
     * 正则不能够直接表示数字范围，所以需要将ip取值限制 转成 正则能够表示的规则：
     *
     *  （一）任何一个1位或2位数字      【1~99】  (\d{1,2})
     *  （二）任何一个以1开头的3位数字   【100~199】    (1\d{2})
     *  （三）任何一个以2开头、第2位在0~4的3位数字    【200~249】   (2[0-4]\d)
     *  （三）任何一个以25开头、第3位在0~5的3位数字   【250~255】   (25[0-5])
     */
    static String quarter = "(\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5])";

    private static Pattern pattern7 = Pattern.compile("(("+quarter+")\\.){3}("+quarter+")");

    public static void main(String[] args) {
        String s = "hello, my name is mike&nbsp;jordan, and i am" +
                "the best basketball player, rank No&nbsp;&nbsp;1";

        System.out.println(replaceAll(pattern, s, "空格"));
        System.out.println(replaceAll(pattern2, s, "空格"));

        String s1 = "ping 12.159.46.200 ... ";
        System.out.println(matchAll(pattern3, s1));
        System.out.println(matchAll(pattern4, s1));

        String s2 = "ID: 042" +
                "SEX: M" +
                "BIRTH: 1967-08-17" +
                "STATUS: Active";
        System.out.println(matchAll(pattern5, s2));
        System.out.println(matchAll(pattern6, s2));

        String s3 = "illegal ip: 666.77.8.999" +
                "legal ip: 12.159.46.200";
        System.out.println(matchAll(pattern7, s3));
    }

    private static List<String> matchAll(Pattern pattern, String source) {
        List<String> matchLs = new ArrayList<>(10);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find())
            matchLs.add(matcher.group());
        return matchLs;
    }

    private static String replaceAll(Pattern pattern, String source, String replaceStr) {
        /**
         * String的replaceAll也是使用正则：Pattern.compile(regex).matcher(this).replaceAll(replacement);
         * 而正则replaceAll的底层使用sb拼接结果: return text.toString(); 所以不会对源字符串造成副作用
         */
        Matcher matcher = pattern.matcher(source);
        return matcher.replaceAll(replaceStr);
    }
}
