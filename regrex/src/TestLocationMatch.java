import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLocationMatch {

   private static Pattern pattern = Pattern.compile("cat");
   // \s会“消费”空格
   private static Pattern pattern1 = Pattern.compile("\\scat\\s");
   // 使用向后向前查找 定位消费内容
   private static Pattern pattern1_ref = Pattern.compile("(?<=\\s)cat(?=\\s)");
   // \b是单词边界（不会消费空格）: \b匹配这样一个位置--位于能够用来构成单词的字符（字母，数字，下划线，也就是与\w匹配的字符）
    // 和一个不能用来构成单词的字符（也就是与\W匹配的字符）之间
    // \b只匹配一个位置，不匹配任何字符，所以"\bcat\b"匹配到的字符串长度是3（c,a,t）
   private static Pattern pattern2 = Pattern.compile("\\bcat\\b");
   private static Pattern pattern3 = Pattern.compile("\\bcap");
   private static Pattern pattern4 = Pattern.compile("cap\\b");

    // ^匹配整个字符串的结尾位置 注意：^在正则字符集合起到求非作用
    private static Pattern pattern5 = Pattern.compile("<\\?xml.*?\\?>");
    private static Pattern pattern6 = Pattern.compile("\\s*^<\\?xml.*?\\?>");
    // $匹配整个字符串的结尾位置 例：web页面里，</html>标签后面不应该再有任何内容
    // 可以使用这个模式检查： </[Hh][Tt][Mm][Ll]>\s*$

    private static Pattern pattern7 = Pattern.compile("(?m)//.*$");

    public static void main(String[] args) {
        String txt = "the cat scattered his food all over the room";

        String txt2 = "the captain wore his cap and cape proudly as \n" +
                "he sat listening to the recap of how his" +
                "crew saved the men from a capsized vessel";

        System.out.println(matchAll(pattern, txt));
        System.out.println(matchAll(pattern1, txt));
        System.out.println(matchAll(pattern1_ref, txt));
        System.out.println(matchAll(pattern2, txt));
        System.out.println(replaceAll(pattern3, txt2, "@@@"));

        // 如果不对$转义，将会抛出异常：Exception in thread "main" java.lang.IllegalArgumentException: Illegal group reference
        /**
         * https://blog.csdn.net/qq_37502106/article/details/88642840
         * 可以看到这里面对“$”符号和"\\"符号进行了处理。出现以上错误的原因是：String的replaceAll(regex, replacement)方法的第一个参数支持正则表达式，
         * 如果参数replacement中出现符号“$”,会按照$1$2的分组模式进行匹配。当编译器发现“$”后跟的不是整数的时候，就会抛出“Illegal group reference”的异常。
         *
         * 处理办法：用JDK提供的方法，对特殊字符进行处理：
         * replacement = java.util.regex.Matcher.quoteReplacement(replacement);
         * 或者手动转义：\\$\\$\\$
         */
        System.out.println(replaceAll(pattern4, txt2, Matcher.quoteReplacement("$$$")));

        String txt3 = "this xml file has a bad start\n" +
                "  <?xml version='1.0' encoding='UTF-8' ?> \n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "                        http://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "</beans>";

        System.out.println(matchAll(pattern5, txt3).size() == 1 ? "合格xml" : "不合格xml");
        System.out.println(matchAll(pattern6, txt3).size() == 1 ? "合格xml" : "不合格xml");

        String js = "ar URL= {\n" +
                "            addBatchTemplate: '/v3/switcher/addBatchTemplate',//添加模板\n" +
                "            getBackDeviceTypeList:'/v3/switcher-device-template/getFrontDeviceTypeList', //获取设备型号列表\n" +
                "            loadConfig: '/v3/switcher/loadConfig',//post 下发设备\n" +
                "            isInvalidEdit: '/v3/switcher/isInvalidEdit',//编辑模板权限判断\n" +
                "            isInvalidConfig: '/v3/switcher/isInvalidConfig',//应用模板权限判断\n" +
                "            getBatchTemplateInfo:'/v3/switcher/getBatchTemplateInfo', //查询模板信息\n" +
                "        };\n";
        System.out.println(matchAll(pattern7, js));
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
