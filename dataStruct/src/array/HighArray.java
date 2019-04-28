package array;

public class HighArray {
    private long[] arr;
    private int nElems;

    public HighArray(int max) {
        arr = new long[max];
        nElems = 0;
    }

    public boolean find(long value) {
        int idx;
        for (idx = 0; idx < nElems; idx++) {
            if (arr[idx] == value)
                break;
        }
        if (idx < nElems)
            return true;
       return false;
    }

    public void insert(long value) {
        arr[nElems++] = value;
    }

    public boolean delete(long value) {
        int idx;
        for (idx = 0; idx < nElems; idx++) {
            if (arr[idx] == value)
                break;
        }
        /*no found*/
        if (idx == nElems)
            return false;

        for (int i = idx; i < nElems-1; i++) {
            arr[i] = arr[i+1];
        }
        nElems--;
        return true;
    }

    public void display() {
        for (int idx = 0; idx < nElems; idx++) {
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }
}
