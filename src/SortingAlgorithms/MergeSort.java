package SortingAlgorithms;

import java.util.Arrays;

/**
 * MergeSort implementation for sorting an array of integers.
 */
public class MergeSort {

    /**
     * Performs merge sort on the input array.
     *
     * @param arrayToSort The array to sort.
     * @return A new sorted array.
     */
    public static int[] mergeSort(int[] arrayToSort) {
        // Do not split further if the array contains only one element.
        if (arrayToSort.length == 1 || arrayToSort.length == 0) return arrayToSort;

        int arrayLength = arrayToSort.length;
        int middleIndex = arrayLength / 2;

        // Break the array into halves
        int[] leftArray = mergeSort(Arrays.copyOfRange(arrayToSort, 0, middleIndex));
        int[] rightArray = mergeSort(Arrays.copyOfRange(arrayToSort, middleIndex, arrayLength));

        return merge(leftArray, rightArray);
    }

    /**
     * Merges two sorted arrays into a single sorted array.
     *
     * @param leftArray  The left sorted array.
     * @param rightArray The right sorted array.
     * @return A new merged and sorted array.
     */
    private static int[] merge(int[] leftArray, int[] rightArray) {
        int leftArrayLength = leftArray.length;
        int rightArrayLength = rightArray.length;
        int[] combinedArray = new int[leftArrayLength + rightArrayLength];
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;

        // Merge arrays while elements remain in both
        while (leftIndex < leftArrayLength && rightIndex < rightArrayLength) {
            int leftValue = leftArray[leftIndex];
            int rightValue = rightArray[rightIndex];
            if (leftValue < rightValue) {
                combinedArray[currentIndex] = leftValue;
                leftIndex++;
            } else {
                combinedArray[currentIndex] = rightValue;
                rightIndex++;
            }
            currentIndex++;
        }

        // Add remaining elements from left array
        while (leftIndex < leftArrayLength) {
            combinedArray[currentIndex] = leftArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        // Add remaining elements from right array
        while (rightIndex < rightArrayLength) {
            combinedArray[currentIndex] = rightArray[rightIndex];
            rightIndex++;
            currentIndex++;
        }

        return combinedArray;
    }

    /**
     * Main method to test MergeSort implementation.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        int[] array = {20, 30, 50, 56, 78, 300, 500, 85, 25, 35, 55, 80, 90, 101, 105, 200};

        // Perform MergeSort
        int[] sortedArray = mergeSort(array);

        // Print the sorted array
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));

        // Additional test cases
        int[] emptyArray = {}; // Test with an empty array
        System.out.println("Empty array: " + Arrays.toString(mergeSort(emptyArray)));

        int[] singleElementArray = {42}; // Test with a single element
        System.out.println("Single element array: " + Arrays.toString(mergeSort(singleElementArray)));

        int[] alreadySortedArray = {1, 2, 3, 4, 5}; // Test with an already sorted array
        System.out.println("Already sorted array: " + Arrays.toString(mergeSort(alreadySortedArray)));

        int[] reverseSortedArray = {5, 4, 3, 2, 1}; // Test with a reverse sorted array
        System.out.println("Reverse sorted array: " + Arrays.toString(mergeSort(reverseSortedArray)));

        int[] duplicateElementsArray = {3, 3, 3, 3}; // Test with duplicate elements
        System.out.println("Duplicate elements array: " + Arrays.toString(mergeSort(duplicateElementsArray)));
    }
}
