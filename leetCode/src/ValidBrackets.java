import java.util.Stack;

public class ValidBrackets {
    public static void main(String[] args) {
        boolean valid = new ValidBrackets().isValid("([)]");
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        if (s.trim().isEmpty())
            return true;

        Stack<Character> stack = new Stack<>();
        Character ch, pop;
        int i;
        for (i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    // 针对"]", "}", ")"
                    if (stack.isEmpty())
                        return false;

                    pop = stack.pop();
                    if (   ch == '}' && pop != '{'
                        || ch == ']' && pop != '['
                        || ch == ')' && pop != '(')
                        return false;

                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
