import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBackTraceRef {

    // 匹配一级标题
    private static Pattern pattern = Pattern.compile("<[Hh]1>.*?</[Hh]1>");

    // 匹配任意级别标题
    private static Pattern pattern1 = Pattern.compile("<[Hh][1-6]>.*?</[Hh][1-6]>");

    // 回溯引用去掉不合法标签 【回溯引用只能用来引用子表达式】
    private static Pattern pattern2 = Pattern.compile("<[Hh]([1-6])>.*?</[Hh]\\1>");

    // 回溯引用匹配重复单词
    private static Pattern pattern3 = Pattern.compile("\\s(\\w+)\\s\\1");

    // 替换中使用回溯
    private static Pattern pattern4 = Pattern.compile("(\\w+[\\w.]*@[\\w.]+\\.\\w+)");
    private static Pattern pattern5 = Pattern.compile("(\\d{3})-(\\d{3})-(\\d{4})");

    public static void main(String[] args) {
        String html = "<BODY>" +
                "<H1>welcome to general expression lesson</H1>" +
                "<H2>regexp is a good tool to handle string</H2>" +
                "<H2>you can be a regex master</H2>" +
                "<H2>this is not valid</H3>" +
                "</BODY>";
        System.out.println(matchAll(pattern, html));
        System.out.println(matchAll(pattern1, html));
        System.out.println(matchAll(pattern2, html));

        String s = "this is a block of of text," +
                "several words here are are" +
                "repeated, and and they" +
                "should not be";
        System.out.println(matchAll(pattern3, s));

        /**
         * 在用于替换的字符串中：通过$1,$2引用子表达式匹配结果
         */
        String s1 = "hi, ben@forta.com is my email address";
        System.out.println(replaceAll(pattern4, s1, "<A HREF='mailto:$1'>$1</A>"));

        String s2 = "313-555-1234 \n" +
                "248-555-9999 \n" +
                "810-555-9000 \n";
        System.out.println(replaceAll(pattern5, s2, "($1) $2-$3"));
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
