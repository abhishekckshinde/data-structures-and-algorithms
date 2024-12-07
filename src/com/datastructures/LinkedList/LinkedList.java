package com.datastructures.LinkedList;

public class LinkedList {

    private Node head;
    private Node tail;

    protected class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("\nLinked List:");
        if (head == null) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Node findMiddleNode(){
        Node slowNode = this.head;
        Node fastNode = this.head;
        while (fastNode.next != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if(fastNode.next != null){
                fastNode = fastNode.next;
            }
        }
        return slowNode;
    }

    public boolean hasLoop(){
        Node slowNode = this.head;
        Node fastNode = this.head;
        if(this.head == null){
            return false;
        }
        while (fastNode.next != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            if(fastNode.next != null){
                fastNode = fastNode.next;
            }
            if(fastNode == slowNode){
                return true;
            }
        }
        return false;
    }

    public void reverse(){
        Node temp = this.head;
        this.head = tail;
        this.tail = temp;
        Node after;
        Node before  = null;

       for (int i = 0; i < 4; i++ ){
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }

    }
}

