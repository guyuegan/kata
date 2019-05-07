import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPureTextAndDot {

    private static final Pattern pattern = Pattern.compile("Ben");
    private static final Pattern pattern02 = Pattern.compile("my");
    /**正则默认是大小写敏感的，预编译模式时，可以指定大小写不敏感选项*/
    private static final Pattern pattern03 = Pattern.compile("ben", Pattern.CASE_INSENSITIVE);

    /**.在正则中可以匹配任意一个字符：字符，数字，字母包括.自身（SQL中有相同功能的是_）*/
    private static final Pattern pattern04 = Pattern.compile("sales.");
    private static final Pattern pattern05 = Pattern.compile(".a.\\.xls");

    public static void main(String[] args) {
        String txt = "Hello, my name is Ben. Please visit my01 website at http://www.forta.com/.";
        Matcher matcher = pattern03.matcher(txt);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        String fileLs = "sales1.xls\n" +
                        "orders3.xls\n" +
                        "sales2.xls\n" +
                        "sales.xls\n" +
                        "sales3.xls\n" +
                        "apac1.xls\n" +
                        "europe2.xls\n" +
                        "na1.xls\n" +
                        "na2.xls\n" +
                        "sa1.xls\n";
        Matcher matcher1 = pattern04.matcher(fileLs);
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }

        Matcher matcher2 = pattern05.matcher(fileLs);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }
    }
}

