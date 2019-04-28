package array;

public class OrderedArray {
    private long[] arr;
    private int nElems;

    public OrderedArray(int max) {
        arr = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long value) {
        int low = 0;
        int high = nElems-1;
        int curIdx;
        while (low <= high) {
            curIdx = (low + high) / 2;
            if (arr[curIdx] == value)
                return curIdx;
            if (arr[curIdx] > value)
                 high = curIdx - 1;
            else
                low = curIdx + 1;
        }
        return -1;
    }

    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (arr[j] > value)
                break;
        }
        for (int k = nElems; k > j; k--) {
            arr[k] = arr[k-1];
        }
        arr[j] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j = find(value);
        if (j == -1)
            return false;
        for (int k = j; k < nElems-1; k++) {
            arr[k] = arr[k+1];
        }
        nElems--;
        return true;
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
