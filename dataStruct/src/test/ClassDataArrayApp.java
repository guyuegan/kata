package test;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassDataArrayApp {
    public static void main(String[] args) {
        /*ClassDataArray arr = new ClassDataArray(10);

        arr.insert("first01", "last01",1);
        arr.insert("first02", "last02",2);
        arr.insert("first03", "last03",3);
        arr.insert("first04", "last04", 4);
        arr.insert("first05", "last05", 5);
        arr.insert("first06", "last06", 6);
        arr.insert("first07", "last07", 7);
        arr.insert("first08", "last08", 8);
        arr.insert("first09", "last09", 9);
        arr.insert("first10", "last10", 10);

        arr.display();

        Person last06 = arr.find("last06");
        if (null == last06)
            System.out.println("not found: last06");
        else
            System.out.println("found: " + last06);

        arr.delete("last03");
        arr.delete("last05");
        arr.delete("last07");

        arr.display();*/

        Person person = new Person("test", "test", 10);
        /*复制了一个引用，作为参数传给函数内部使用*/
        Person newPerson = changeName(person);
        System.out.println(person);
        System.out.println(newPerson);

    }

    public  static Person changeName(Person p) {
        p.setLastName("change in function");
        p = new Person("newPerson", "newPerson", 20);
        return p;
    }
}
