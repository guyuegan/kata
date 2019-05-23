import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLookAround {

    // 匹配标签+内容
    private static Pattern pattern = Pattern.compile("<([Tt][Ii][Tt][Ll][Ee])>(.*)</\\1>");
    // 匹配内容
    private static Pattern pattern2 = Pattern.compile("(?<=<[Tt][Ii][Tt][Ll][Ee]>)(.*)(?=(</[Tt][Ii][Tt][Ll][Ee]>))");
    // 匹配价格
    private static Pattern pattern3 = Pattern.compile("[0-9.]+");

    private static Pattern pattern4 = Pattern.compile("\\$[0-9.]+");
    // 向后查找
    private static Pattern pattern5 = Pattern.compile("(?<=\\$)[0-9.]+");

    public static void main(String[] args) {
        String s = "<HEAD>" +
                "<TITLE>BEN FORTA'S HOMEPAGE</TITLE>" +
                "</HEAD>";
        // 获取匹配的整个结果
        System.out.println(matchAll(pattern, s));
        // 获取匹配的结果中，某个子表达式的匹配部分（子表达式）
        System.out.println(matchTagContent(pattern, s));
        // 前后查找匹配
        System.out.println(matchAll(pattern2, s));

        String s1 = "ABC01: $23.45" +
                "HGG42: $5.31" +
                "CFMX1: $899.00" +
                "XTC99: $69.96" +
                "Total items found: 4";
        System.out.println(matchAll(pattern3, s1));
        System.out.println(matchAll(pattern4, s1));
        System.out.println(matchAll(pattern5, s1));
    }

    private static List<String> matchTagContent(Pattern pattern, String source) {
        List<String> matchLs = new ArrayList<>(10);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find())
            // matcher.group(int subExpIdx)
            matchLs.add(matcher.group(2));
        return matchLs;
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
