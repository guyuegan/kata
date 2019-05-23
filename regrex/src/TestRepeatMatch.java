import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRepeatMatch {
    // "\\w+@\\w+.\\w+"模式匹配不了 ben.forta@forta.com  因为\w是[a-zA-Z0-9_],只能匹配数字字母下划线
    private static Pattern pattern = Pattern.compile("\\w+@\\w+.\\w+");
    private static Pattern pattern04 = Pattern.compile("[\\w.]+@[\\w.]+\\w+");
    //[0-9]+匹配一个或多个数字,等效于\d+  [0-9+]匹配一个数字或者一个+【其实匹配+最好是进行转义\+, 但是在正则的字符集合里面好像可以不转义】
    // + 等效于 {1,}
    private static Pattern pattern02 = Pattern.compile("[0-9]+");
    private static Pattern pattern03 = Pattern.compile("[0-9+]");

    // 如果首字符是. 则不是合法的邮箱，pattern04会匹配出.ben@forta.com， 所以改造为pattern05 【* 匹配零次或多次 等价于 {0,}】
    private static Pattern pattern05 = Pattern.compile("\\w+[\\w.]*@[\\w.]+\\w+");
    // ? 匹配0次【false】或1【true】次 等价于{0,1}
    private static Pattern pattern06 = Pattern.compile("https?://[\\w./]+");
    // 精确重复次数
    private static Pattern pattern07 = Pattern.compile("#[\\da-fA-F]{6}");
    // 重复次数区间
    private static Pattern pattern08 = Pattern.compile("\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}");
    // 至少重复多少次， 匹配大于100没有的价格
    private static Pattern pattern09 = Pattern.compile("\\$\\d{3,}");

    // 【贪婪模式】
    private static Pattern pattern11 = Pattern.compile("<[Bb]>.*</[Bb]>");
    // 防止过度匹配【懒惰模式】
    /**
     *  贪婪模式    懒惰模式
     *  *           *?
     *  +           +?
     *  {n,}        {n,}?
     */
    private static Pattern pattern10 = Pattern.compile("<[Bb]>.*?</[Bb]>");

    public static void main(String[] args) {
        String txt = "send personal email to ben@forta.com or ben.forta@forta.com. for questions " +
                "about a book use support@forta.com or ben@urgent.forta.com. feel free to send" +
                "unsolicited email to spam@forta.com (wouldn't it be" +
                "nice if it were that simple, huh?). 88 1 + 6";

        Matcher matcher = pattern.matcher(txt);
        while (matcher.find())
            System.out.println(matcher.group());

        Matcher matcher02 = pattern02.matcher(txt);
        while (matcher02.find())
            System.out.println(matcher02.group());

        Matcher matcher03 = pattern03.matcher(txt);
        while (matcher03.find())
            System.out.println(matcher03.group());

        Matcher matcher04 = pattern04.matcher(txt);
        while (matcher04.find())
            System.out.println(matcher04.group());

        String txt02 = "hello .ben@forta.com is my email address";
        Matcher matcher05 = pattern05.matcher(txt02);
        while (matcher05.find())
            System.out.println(matcher05.group());

        String txt03 = "the url is http://www.forta.com/, to connect" +
                "securely use https://www.forta.com/ instead.";
        Matcher matcher1 = pattern06.matcher(txt03);
        while (matcher1.find())
            System.out.println(matcher1.group());

        String html = "<body bgcolor='#336633' text='#ffffff' width='666' height='444'>";
        Matcher matcher2 = pattern07.matcher(html);
        while (matcher2.find())
            System.out.println(matcher2.group());

        String txt05 = "4/8/03\n" +
                "10-6-2004\n" +
                "2/2/2\n" +
                "01-01-01\n";
        Matcher matcher3 = pattern08.matcher(txt05);
        while (matcher3.find())
            System.out.println(matcher3.group());

        String price = "$496.80 $1290.69 $26.43 $613.42 $7.61 $414.90 $25.00";
        Matcher matcher4 = pattern09.matcher(price);
        while (matcher4.find())
            System.out.println(matcher4.group());

        String htm = "<B>i am a B</B> <b>i am a b too</b>";
        Matcher matcher5 = pattern11.matcher(htm);
        while (matcher5.find())
            System.out.println(matcher5.group());

        Matcher matcher6 = pattern10.matcher(htm);
        while (matcher6.find())
            System.out.println(matcher6.group());
    }
}
