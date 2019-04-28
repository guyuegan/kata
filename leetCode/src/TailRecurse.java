public class TailRecurse {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(tailfib(60, 1, 1));
        System.out.println("tailfib(): " + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        System.out.println(fib(60));
        System.out.println("fib(): " + (System.currentTimeMillis()-start));
    }

    public static int tailfib(int n,int prev1,int prev2) {
        if (n < 2) {
            return prev1;
        }
        return tailfib(n-1, prev2,prev1 + prev2);
    }

    public static int fib(int n) {
        if (n <= 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}

