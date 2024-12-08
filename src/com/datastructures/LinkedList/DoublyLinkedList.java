package com.datastructures.LinkedList;


/**
 * A doubly linked list implementation in Java.
 * This class provides basic operations such as insertion, deletion, reversal, and custom utilities
 * like checking for palindromes and swapping pairs.
 */
public class DoublyLinkedList {
    private Node head; // Pointer to the head of the list
    private Node tail; // Pointer to the tail of the list
    private int length; // Tracks the number of nodes in the list

    // Constructor to initialize the doubly linked list with a single node
    public DoublyLinkedList(int data) {
        Node node = new Node(data);
        setHead(node);
        setTail(node);
        length = 1;
    }

    // Getters and setters for head and tail nodes
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

    /**
     * Appends a new node with the given value at the end of the list.
     */
    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        length++;
    }

    /**
     * Prepends a new node with the given value at the start of the list.
     */
    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        length++;
    }

    /**
     * Removes and returns the last node in the list.
     */
    public Node removeLast() {
        if (length == 0) return null;

        Node removedNode = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            removedNode.setPrevious(null);
        }
        length--;
        return removedNode;
    }

    /**
     * Retrieves a node at a given index (0-based).
     */
    public Node get(int index) {
        if (index < 0 || index >= length) return null;

        Node current;
        if (index < length / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = length - 1; i > index; i--) {
                current = current.getPrevious();
            }
        }
        return current;
    }

    /**
     * Updates the value of a node at a given index.
     */
    public boolean set(int index, int value) {
        Node node = get(index);
        if (node == null) return false;

        node.setData(value);
        return true;
    }

    /**
     * Inserts a new node at the specified index.
     */
    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;

        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.getNext();

        newNode.setPrevious(before);
        newNode.setNext(after);
        before.setNext(newNode);
        after.setPrevious(newNode);

        length++;
        return true;
    }

    /**
     * Reverses the list in place.
     */
    public void reverse() {
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.getPrevious();
            current.setPrevious(current.getNext());
            current.setNext(temp);
            current = current.getPrevious();
        }

        temp = head;
        head = tail;
        tail = temp;
    }

    /**
     * Checks if the list is a palindrome.
     */
    public boolean isPalindrome() {
        Node front = head;
        Node end = tail;

        while (front != null && end != null && front != end && front.getPrevious() != end) {
            if (front.getData() != end.getData()) return false;

            front = front.getNext();
            end = end.getPrevious();
        }
        return true;
    }

    /**
     * Swaps adjacent pairs of nodes in the list.
     */
    public void swapPairs() {
        if (head == null || head.getNext() == null) return;

        Node current = head;
        while (current != null && current.getNext() != null) {
            Node next = current.getNext();
            if (current.getPrevious() != null) {
                current.getPrevious().setNext(next);
            } else {
                head = next;
            }

            if (next.getNext() != null) {
                next.getNext().setPrevious(current);
            } else {
                tail = current;
            }

            current.setNext(next.getNext());
            next.setPrevious(current.getPrevious());
            next.setNext(current);
            current.setPrevious(next);

            current = current.getNext();
        }
    }

    /**
     * Prints the list elements.
     */
    public void printList() {
        Node current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.getData());
            if (current.getNext() != null) System.out.print(", ");
            current = current.getNext();
        }
        System.out.println("]");
    }

    /**
     * Represents a node in the doubly linked list.
     */
    public static class Node {
        private int data;
        private Node next;
        private Node previous;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
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
    }

    // Main method for testing the implementation
    public static void main(String[] args) {
        // Initialize the list with a single element
        DoublyLinkedList list = new DoublyLinkedList(12);

        System.out.println("Initial List:");
        list.printList(); // Output: [12]

        // Append elements
        list.append(10);
        list.append(15);
        list.append(25);
        System.out.println("\nAfter Appending 10, 15, 25:");
        list.printList(); // Output: [12, 10, 15, 25]

        // Prepend elements
        list.prepend(5);
        System.out.println("\nAfter Prepending 5:");
        list.printList(); // Output: [5, 12, 10, 15, 25]

        // Get element at index
        System.out.println("\nElement at index 2: " + list.get(2).getData()); // Output: 10

        // Set element at index
        list.set(2, 20);
        System.out.println("\nAfter Setting index 2 to 20:");
        list.printList(); // Output: [5, 12, 20, 15, 25]

        // Insert element at index
        list.insert(2, 30);
        System.out.println("\nAfter Inserting 30 at index 2:");
        list.printList(); // Output: [5, 12, 30, 20, 15, 25]

        // Remove the last element
        list.removeLast();
        System.out.println("\nAfter Removing Last Element:");
        list.printList(); // Output: [5, 12, 30, 20, 15]

        // Reverse the list
        list.reverse();
        System.out.println("\nAfter Reversing the List:");
        list.printList(); // Output: [15, 20, 30, 12, 5]

        // Check if the list is a palindrome
        System.out.println("\nIs Palindrome? " + list.isPalindrome()); // Output: false

        // Create a palindrome list and test
        DoublyLinkedList palindromeList = new DoublyLinkedList(1);
        palindromeList.append(2);
        palindromeList.append(3);
        palindromeList.append(2);
        palindromeList.append(1);
        System.out.println("\nPalindrome List:");
        palindromeList.printList(); // Output: [1, 2, 3, 2, 1]
        System.out.println("Is Palindrome? " + palindromeList.isPalindrome()); // Output: true

        // Swap pairs
        list.swapPairs();
        System.out.println("\nAfter Swapping Pairs in the Original List:");
        list.printList(); // Output: [20, 15, 12, 30, 5]

        // Final List Summary
        System.out.println("\nFinal State of Original List:");
        list.printList(); // Output: [20, 15, 12, 30, 5]
    }

}
