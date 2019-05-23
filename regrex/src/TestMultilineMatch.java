import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMultilineMatch {

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

        // Pattern.MULTILINE表示多行匹配 等同于：Pattern.compile("(?m)^[ns]a.\\.xls")
        Pattern pattern = Pattern.compile("^[ns]a.\\.xls", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(fileLs);
        String replaceResult = matcher.replaceAll("多行匹配");
        System.out.println(replaceResult);

        Pattern pattern02 = Pattern.compile("^[ns]a.\\.xls");
        Matcher matcher02 = pattern02.matcher(fileLs);
        String replaceResult02 = matcher02.replaceAll("整串匹配");
        System.out.println(replaceResult02);
    }
}
