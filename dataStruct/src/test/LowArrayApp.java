package test;

import array.LowArray;

public class LowArrayApp {
    public static void main(String[] args) {
        LowArray lowArray = new LowArray(10);
        int nElems = 0;

        lowArray.set(0, 77);
        lowArray.set(1, 99);
        lowArray.set(2, 44);
        lowArray.set(3, 55);
        lowArray.set(4, 22);
        lowArray.set(5, 66);
        lowArray.set(6, 33);
        lowArray.set(7, 88);
        lowArray.set(8, 11);
        lowArray.set(9, 00);
        nElems = 10;

        for (int i = 0; i < nElems; i++) {
            System.out.print(lowArray.get(i) + " ");
        }
        System.out.println();

        int searchKey = 26;
        int idx;
        for (idx = 0; idx < nElems; idx++) {
            if (lowArray.get(idx) == searchKey)
                break;
        }
        if (idx == nElems)
            System.out.println("can't find " + searchKey);
        else
            System.out.println("found " + searchKey);

        for (idx = 0; idx < nElems; idx++) {
            if (lowArray.get(idx) == 55)
                break;
        }

        for (int i = idx; i < nElems-1; i++) {
            lowArray.set(i, lowArray.get(i+1));
        }
        nElems--;

        for (idx = 0; idx < nElems; idx++) {
            System.out.print(lowArray.get(idx) + " ");
        }
        System.out.println();
    }
}
