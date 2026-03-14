package edu.grinnell.csc207.sorts;

import java.util.Arrays;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i   the first index to swap
     * @param j   the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * 
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * 
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * 
     * <pre>
     * [ i elements in order | unprocessed ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T compared = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(compared) > 0) {
                arr[j + 1] = arr[i];
                j--;
            }
            arr[j + 1] = compared;
        }
    }

    /**
     * Sorts the array according to the merge sort algorithm:
     * <ol>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * <li>Merge the two sorted halves into a sorted whole.</li>
     * </ol>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return;
        }
        int middle = len / 2;
        T[] leftArr = Arrays.copyOfRange(arr, 0, middle);
        T[] rightArr = Arrays.copyOfRange(arr, middle, len);
        int rightPointer = 0;
        for (int i = 0; i < len; i++) {
            if (i < middle) {
                leftArr[i] = arr[i];
            } else {
                rightArr[rightPointer] = arr[i];
                rightPointer++;
            }
        }
        mergeSort(leftArr);
        mergeSort(rightArr);
        merge(arr, leftArr, rightArr);
    }

    /**
     * Helper for merge sort algorithm:
     * Merges the two sorted halves into a sorted whole.
     *
     * @param <T>      the carrier type of the array
     * @param arr      the array to merge into
     * @param leftArr  the sorted left half
     * @param rightArr the sorted right half
     */
    public static <T extends Comparable<? super T>> void merge(T[] arr, T[] leftArr, T[] rightArr) {
        // lengths or arrays
        int leftLen = leftArr.length;
        int rightLen = rightArr.length;

        // indeces to walk over the arrays
        int idxLeft = 0;
        int idxRight = 0;
        int idxArr = 0;

        while (idxLeft < leftLen && idxRight < rightLen) {
            if (leftArr[idxLeft].compareTo(rightArr[idxRight]) < 0) {
                arr[idxArr] = leftArr[idxLeft];
                idxArr++;
                idxLeft++;
            } else {
                arr[idxArr] = rightArr[idxRight];
                idxArr++;
                idxRight++;
            }
        }

        // if any elements left in the left array
        while (idxLeft < leftLen) {
            arr[idxArr] = leftArr[idxLeft];
            idxArr++;
            idxLeft++;
        }

        // if any elements left in the right array
        while (idxRight < rightLen) {
            arr[idxArr] = rightArr[idxRight];
            idxArr++;
            idxRight++;
        }
    }

    /**
     * Sorts the array according to the quick sort algorithm:
     * <ol>
     * <li>Choose a pivot value and partition the array according to the pivot.</li>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * </ol>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {

        if (arr.length > 1) {
            quickSortHelper(arr, 0, arr.length - 1);
        }

    }

    public static <T extends Comparable<? super T>> void quickSortHelper(T[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int piovtINDEX = partition(arr, low, high);

        quickSortHelper(arr, low, piovtINDEX - 1);
        quickSortHelper(arr, piovtINDEX + 1, high);
    }

    public static <T extends Comparable<? super T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = low - 1;
        
        for( int j = low; j < high; j++) {
            
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                
                swap(arr, i, j);
            }
       }

       swap(arr, i+1, high);
       return i + 1;
    }
}