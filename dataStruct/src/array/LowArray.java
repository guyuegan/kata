package array;

public class LowArray {
    private long[] arr;

    public LowArray(int max) {
        arr = new long[max];
    }

    public void set(int index, long value) {
        arr[index] = value;
    }

    public long get(int index) {
        return arr[index];
    }
}
