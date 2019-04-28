public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int reverse = 0;
        int lastDigit;
        while (x != 0) {
            lastDigit = x % 10;
            reverse = reverse * 10 + lastDigit;
            x /= 10;
        }

        return (x == reverse);
    }
}
