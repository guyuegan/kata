package test;

import array.HighArray;

public class HighArrayApp {
    public static void main(String[] args) {
        HighArray highArray = new HighArray(10);

        highArray.insert(77);
        highArray.insert(99);
        highArray.insert(44);
        highArray.insert(55);
        highArray.insert(22);
        highArray.insert(66);
        highArray.insert(33);
        highArray.insert(88);
        highArray.insert(11);
        highArray.insert(00);

        highArray.display();

        int searchKey = 56;
        if (highArray.find(searchKey))
            System.out.println("found " + searchKey);
        else
            System.out.println("can't find " + searchKey);

        highArray.delete(00);
        highArray.delete(55);
        highArray.delete(99);

        highArray.display();
    }
}
