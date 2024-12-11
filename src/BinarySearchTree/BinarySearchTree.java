package BinarySearchTree;

import java.util.ArrayList;

/**
 * A simple implementation of a Binary Search Tree (BST) in Java.
 * Supports operations like insertion, search (contains), deletion, and traversal.
 */
public class BinarySearchTree {
    private Node root;

    /**
     * Inserts a value into the BST.
     *
     * @param value the value to insert
     */
    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);

        if (value < currentNode.getValue()) {
            currentNode.setLeft(insert(currentNode.getLeft(), value));
        } else if (value > currentNode.getValue()) {
            currentNode.setRight(insert(currentNode.getRight(), value));
        }
        // Duplicate values are ignored in this implementation.
        return currentNode;
    }

    /**
     * Checks if a value exists in the BST.
     *
     * @param value the value to search for
     * @return true if the value exists, false otherwise
     */
    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node currentNode, int value) {
        if (currentNode == null) return false;

        if (value == currentNode.getValue()) return true;

        return value < currentNode.getValue()
                ? contains(currentNode.getLeft(), value)
                : contains(currentNode.getRight(), value);
    }

    /**
     * Deletes a value from the BST if it exists.
     *
     * @param value the value to delete
     */
    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node currentNode, int value) {
        if (currentNode == null) return null;

        if (value < currentNode.getValue()) {
            currentNode.setLeft(delete(currentNode.getLeft(), value));
        } else if (value > currentNode.getValue()) {
            currentNode.setRight(delete(currentNode.getRight(), value));
        } else {
            // Node to delete found
            // Case 1: Node with only one child or no child
            if (currentNode.getLeft() == null) return currentNode.getRight();
            if (currentNode.getRight() == null) return currentNode.getLeft();

            // Case 2: Node with two children
            // Replace with the smallest value in the right subtree (inorder successor)
            currentNode.setValue(findMin(currentNode.getRight()).getValue());
            currentNode.setRight(delete(currentNode.getRight(), currentNode.getValue()));
        }

        return currentNode;
    }

    private Node findMin(Node currentNode) {
        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    /**
     * Prints the BST in an inorder traversal (sorted order).
     */
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    public void sortedArrayToBST(int[] nums){
        this.root = sortedArrayToBST(nums, 0, nums.length -1);
    }

    private Node sortedArrayToBST(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int middleIndex = left + (right-left) /   2;
        Node node = new Node(nums[middleIndex]);
        node.setLeft(sortedArrayToBST(nums, left, middleIndex-1));
        node.setRight(sortedArrayToBST(nums, middleIndex+1, right));
        return node;
    }
    public void invert() {
        root = invertTree(root);
    }

    private Node invertTree(Node node){
        if(node == null)
            return null;
        Node temp = node.right;
        node.right = node.left;
        node.left = temp;
        invertTree(node.right);
        invertTree(node.left);
        return node;

    }
    private void printInOrder(Node currentNode) {
        if (currentNode != null) {
            printInOrder(currentNode.getLeft());
            System.out.print(currentNode.getValue() + " ");
            printInOrder(currentNode.getRight());
        }
    }

    public ArrayList<Integer> DFSPreorder(){
        ArrayList<Integer> results = new ArrayList<>();
        DFSPreorder(root, results);
        return results;
    }

    public void DFSPreorder(Node currentNode, ArrayList<Integer> results){
        if(currentNode != null){
            results.add(currentNode.value);
            DFSPreorder(currentNode.left, results);
            DFSPreorder(currentNode.right,results  );
        }
    }

    public ArrayList<Integer> DFSPostorder(){
        ArrayList<Integer> results = new ArrayList<>();
        DFSPostorder(this.root, results);
        return results;
    }

    public void DFSPostorder(Node currentNode, ArrayList<Integer> result){
        if (currentNode != null) {
            DFSPostorder(currentNode.getLeft(), result);  // Visit left subtree
            DFSPostorder(currentNode.getRight(), result); // Visit right subtree
            result.add(currentNode.getValue());        // Visit current node
        }
    }
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert values
        bst.insert(50);
        bst.insert(70);
        bst.insert(20);
        bst.insert(19);
        bst.insert(30);
        bst.insert(69);
        bst.insert(90);

        // Check for values
        System.out.println("Contains 50? " + bst.contains(50)); // true
        System.out.println("Contains 30? " + bst.contains(30)); // true
        System.out.println("Contains 100? " + bst.contains(100)); // false

        // Print BST
        System.out.print("Inorder traversal: ");
        bst.printInOrder();

        System.out.println("Pre Order Traversal :");
        bst.DFSPreorder().forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        System.out.println("Post Order Traversal :");
        bst.DFSPostorder().forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        // Delete values
        System.out.println("Deleting 70...");
        bst.delete(70);
        System.out.print("Inorder traversal after deletion: ");
        bst.printInOrder();

        System.out.println("Deleting 50...");
        bst.delete(50);
        System.out.print("Inorder traversal after deletion: ");
        bst.printInOrder();
    }

    /**
     * Node class represents a single node in the BST.
     */
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
