package com.datastructures.LinkedList;

public class MainClass {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(10);
        linkedList.append(11);
        linkedList.append(12);
        linkedList.append(13);


      linkedList.printList();
        System.out.println("--------");
        linkedList.reverse();
        System.out.println("--------");

        linkedList.printList();

    }
}
