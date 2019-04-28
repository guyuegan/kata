package test;

import array.OrderedArray;

public class OrderedApp {
    public static void main(String[] args) {
        OrderedArray orderedArray = new OrderedArray(10);

        orderedArray.insert(77);
        orderedArray.insert(99);
        orderedArray.insert(44);
        orderedArray.insert(55);
        orderedArray.insert(22);
        orderedArray.insert(66);
        orderedArray.insert(33);
        orderedArray.insert(88);
        orderedArray.insert(11);
        orderedArray.insert(00);

        orderedArray.display();

        int searchKey = 55;
        if (orderedArray.find(searchKey) != -1)
            System.out.println("found " + searchKey);
        else
            System.out.println("can't find " + searchKey);

        orderedArray.delete(00);
        orderedArray.delete(55);
        orderedArray.delete(99);

        orderedArray.display();
    }
}
