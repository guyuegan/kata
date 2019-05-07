import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMetaChar {
    // 对元字符进行转义
    private static Pattern pattern = Pattern.compile("myArray\\[0\\]");
    // 匹配 \
    private static Pattern pattern02 = Pattern.compile("\\\\");
    // 匹配空白字符 \r\n 是 回车+换行
    private static Pattern pattern03 = Pattern.compile("\r\n\r\n", Pattern.MULTILINE);
    // 简写元字符：
    /* \d 即 [0-9];          \D 即 [^0-9] 【注意：从小到大】*/
    /* \w 即 [a-zA-Z0-9_];   \W 即 [^a-zA-Z0-9_]*/
    /* \s 即 [\f\n\r\t\v];   \W 即 [^\f\n\r\t\v]*/
    // 这个模式匹配不出myArray[10], 需要加上重复次数才行：myArray\[\d+\]
    private static Pattern pattern04 = Pattern.compile("myArray\\[\\d\\]");

    // 这个模式匹配的字符串，必须是6个字符，所以5个纯数字不会匹配（\w\d\w\d\w\d?可以匹配5个纯数字）
    private static Pattern pattern05 = Pattern.compile("\\w\\d\\w\\d\\w\\d");

    public static void main(String[] args) {
        String js = "var myArray = new Array(); \n" +
                "... \n" +
                "if (myArray[0] == 0 || myArray[10] == 10) { \n" +
                "... \n" +
                "}";
        Matcher matcher = pattern.matcher(js);
        if (matcher.find())
            System.out.println(matcher.group());

        String url = "\\home\\ben\\sales";
        Matcher matcher1 = pattern02.matcher(url);
        while (matcher1.find())
            System.out.println(matcher1.group());

        String txt = "101, ben forta " +
                "102, jim james " +
                "       " +
                "103, roberta robertson ";
        Matcher matcher2 = pattern03.matcher(txt);
        while (matcher2.find())
            System.out.println(matcher2.group() + "ok");

        Matcher matcher3 = pattern04.matcher(js);
        while (matcher3.find())
            System.out.println(matcher3.group());

        String txt2 = "11213 a1c2e3 48075 48237 m1b4f2 90046 h1h2h3 123456";
        Matcher matcher4 = pattern05.matcher(txt2);
        while (matcher4.find())
            System.out.println(matcher4.group());


    }
}
