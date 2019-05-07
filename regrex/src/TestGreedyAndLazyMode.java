import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestGreedyAndLazyMode {
    public static void main(String[] args) {
        String string = "<books><book>西游记</book><book>三国演义</book><book>水浒传</book></books>";

        Pattern pattern = Pattern.compile("<book>.*</book>");
        Matcher matcher = pattern.matcher(string);
        String replaceResult = matcher.replaceFirst("贪婪模式");
        System.out.println(replaceResult);

        Pattern pattern02 = Pattern.compile("<book>.*?</book>");
        Matcher matcher02 = pattern02.matcher(string);
        String replaceResult02 = matcher02.replaceFirst("懒惰模式");
        System.out.println(replaceResult02);


    }
}
