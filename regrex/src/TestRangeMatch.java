import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRangeMatch {

    // pattern会匹配到cat.xls
    private static Pattern pattern = Pattern.compile(".a.\\.xls");
    // pattern02会匹配到usa1.xls
    private static Pattern pattern02 = Pattern.compile("[ns]a.\\.xls");
    // Pattern.MULTILINE表示多行匹配 等同于：Pattern.compile("(?m)^[ns]a.\\.xls")
    private static Pattern pattern03 = Pattern.compile("^[ns]a.\\.xls", Pattern.MULTILINE);
    // 这种用法适合局部忽略大小写的匹配
    private static Pattern pattern04 = Pattern.compile("[Rr]eg[eE]x");

    // [0123456789]可以缩写为[0-9] (从小到大)，或者用特殊字符：\\d
    /* - 是特殊的元字符，作为元字符它只能用在[]中*/
    private static Pattern pattern05 = Pattern.compile("^[ns]a[0123456789]\\.xls", Pattern.MULTILINE);

    private static Pattern pattern06 = Pattern.compile("#[0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f]");

    // 取非匹配
    /* ^ 作用范围：集合里的所有字符或字符区间*/
    private static Pattern pattern07 = Pattern.compile("[ns]a[^0-9]\\.xls");

    public static void main(String[] args) {
        String fileLs = "sales1.xls\n" +
                "orders3.xls\n" +
                "sales2.xls\n" +
                "sales.xls\n" +
                "sales3.xls\n" +
                "apac1.xls\n" +
                "europe2.xls\n" +
                "na1.xls\n" +
                "na2.xls\n" +
                "cat.xls\n" +
                "usa1.xls\n" +
                "sam.xls\n" +
                "sa1.xls\n";
        Matcher matcher = pattern05.matcher(fileLs);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        String txt = "The phrase 'regular expression' is often abbreviated as RegEx or regex";
        Matcher matcher1 = pattern04.matcher(txt);
        while (matcher1.find())
            System.out.println(matcher1.group());

        String html = "<body bgcolor='#336633' text='#ffffff' width='666' height='444'>";
        Matcher matcher2 = pattern06.matcher(html);
        while (matcher2.find())
            System.out.println(matcher2.group());

        Matcher matcher3 = pattern07.matcher(fileLs);
        while (matcher3.find())
            System.out.println(matcher3.group());
    }
}
