package com.datastructures.LinkedList;

import java.util.Optional;

public class DoublyLinkedList {
    private Node head;
    private Node tail;

    private int length;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public DoublyLinkedList(int data) {
        Node node = new Node(data);
        setHead(node);
        setTail(node);
        length = 1;
    }

    public void append(int value){
        Node newNode = new Node(value);
        if(length == 0){
            head = newNode;
            tail = newNode;
            length++;
            return;
        }
        newNode.setPrevious(getTail());
        tail.next = newNode;
        tail = newNode;
        length++;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeLast(){
        if(length == 0){
            return null;
        }
        Node removedNode = tail;
        if(length == 1){
            head = null;
            tail = null;
        }else{
            tail = tail.previous;
            tail.next = null;
            removedNode.previous = null;
        }
        length--;
        return removedNode;
    }

    public Node get(int index){
        if(index < 0 || index >= length){
            return null;
        }
        Node indexNode = head;
        if(index < length/2){
            for (int position = 0; position < index; position++){
                indexNode = indexNode.next;
            }
        }else {
            indexNode = tail;
            for (int position = length -1; position > index; position--){
                indexNode = indexNode.previous;
            }
        }
        return indexNode;
    }

    public boolean set(int index, int value){
        Node selectedNode = get(index);
        return  Optional.ofNullable(selectedNode).map(node -> {
            node.setData(value);
            return true;
        }).orElse(false);
    }

    public boolean insert(int index, int value) {
        if(index < 0 || index > length) return false;
        if(index == 0) {
            prepend(value);
            return true;
        }
        if(index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;
        newNode.previous = before;
        newNode.next = after;
        before.next = newNode;
        after.previous = newNode;
        length++;
        return true;
    }

    public void printList(){
        Node element = getHead();
        System.out.print("[");
        while (element != null){
            System.out.print(element.getData() + ",");
            element = element.getNext();
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        DoublyLinkedList singleList = new DoublyLinkedList(12);
        singleList.append(10);
        singleList.append(15);
        singleList.printList();
        System.out.println("=======");
        boolean setResult = singleList.set(2, 20);
        System.out.println(setResult);
        singleList.set(3, 11);
        singleList.insert(3, 11);
        singleList.insert(2, 36);
        singleList.printList();
        System.out.println(singleList.get(0).data);
        System.out.println(singleList.get(1).data);
        System.out.println(singleList.get(2).data);
        System.out.println(singleList.get(3).data);
    }

    public static class Node {
        private int data;
        private Node next;

        private Node previous;

        public Node(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

    }
}
