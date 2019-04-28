package array;

import test.Person;

public class ClassDataArray {
    private Person[] arr;
    private int nElems;

    public ClassDataArray(int max) {
        arr = new Person[max];
        nElems = 0;
    }

    public Person find(String searchName) {
        int idx = findIdx(searchName);
        return idx == -1 ? null : arr[idx];
    }

    public int findIdx(String searchName) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (arr[j].getLastName().equals(searchName))
                break;
        }

        if (j == nElems)
            return -1;

        return j;
    }

    public void insert(String firstName, String lastName, int age) {
        arr[nElems] = new Person(firstName, lastName, age);
        nElems++;
    }

    public boolean delete(String searchName) {
        int idx = findIdx(searchName);
        if (idx == -1)
            return false;

        for (int k = idx; k < nElems-1; k++)
            arr[k] = arr[k+1];

        nElems--;
        return true;
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(arr[i]);
        }
    }


}
