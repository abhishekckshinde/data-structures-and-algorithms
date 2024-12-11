package SortingAlgorithms;

import java.util.Arrays;

/**
 * QuickSort implementation for sorting an array of integers.
 */
public class QuickSort {

    /**
     * Partitions the array around a pivot element such that
     * elements less than the pivot are on the left and elements
     * greater than the pivot are on the right.
     *
     * @param arrayToSort The array to partition.
     * @param pivotIndex The index of the pivot element.
     * @param endIndex The index of the last element in the current subarray.
     * @return The final position of the pivot element.
     */
    private static int pivot(int[] arrayToSort, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;

        // Traverse the subarray and rearrange elements
        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            if (arrayToSort[i] < arrayToSort[pivotIndex]) {
                swapIndex++;
                swap(arrayToSort, swapIndex, i);
            }
        }
        // Place the pivot element in its correct position
        swap(arrayToSort, swapIndex, pivotIndex);
        return swapIndex;
    }

    /**
     * Recursive QuickSort method to sort the array.
     *
     * @param arrayToSort The array to sort.
     * @param left The starting index of the subarray.
     * @param right The ending index of the subarray.
     */
    private static void quickSort(int[] arrayToSort, int left, int right) {
        if (left < right) {
            // Partition the array and get the pivot index
            int pivotIndex = pivot(arrayToSort, left, right);

            // Recursively sort elements before and after the pivot
            quickSort(arrayToSort, left, pivotIndex - 1);
            quickSort(arrayToSort, pivotIndex + 1, right);
        }
    }

    /**
     * Overloaded method to initiate QuickSort.
     *
     * @param unsortedArray The array to sort.
     */
    private static void quickSort(int[] unsortedArray) {
        quickSort(unsortedArray, 0, unsortedArray.length - 1);
    }

    /**
     * Swaps two elements in the array.
     *
     * @param array The array in which elements will be swapped.
     * @param swapIndex The index of the first element.
     * @param secondIndex The index of the second element.
     */
    private static void swap(int[] array, int swapIndex, int secondIndex) {
        int temp = array[swapIndex];
        array[swapIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    /**
     * Main method to test QuickSort implementation.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        int[] array = {4, 6, 1, 7, 3, 2, 5}; // Example array to sort

        // Perform QuickSort
        quickSort(array);

        // Print the sorted array
        System.out.println("Sorted array: " + Arrays.toString(array));

        // Additional test cases
        int[] emptyArray = {}; // Test with an empty array
        quickSort(emptyArray);
        System.out.println("Empty array: " + Arrays.toString(emptyArray));

        int[] singleElementArray = {42}; // Test with a single element
        quickSort(singleElementArray);
        System.out.println("Single element array: " + Arrays.toString(singleElementArray));

        int[] alreadySortedArray = {1, 2, 3, 4, 5}; // Test with an already sorted array
        quickSort(alreadySortedArray);
        System.out.println("Already sorted array: " + Arrays.toString(alreadySortedArray));

        int[] reverseSortedArray = {5, 4, 3, 2, 1}; // Test with a reverse sorted array
        quickSort(reverseSortedArray);
        System.out.println("Reverse sorted array: " + Arrays.toString(reverseSortedArray));

        int[] duplicateElementsArray = {3, 3, 3, 3}; // Test with duplicate elements
        quickSort(duplicateElementsArray);
        System.out.println("Duplicate elements array: " + Arrays.toString(duplicateElementsArray));
    }
}
