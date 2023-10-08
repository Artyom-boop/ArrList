package org.example;

import java.util.Comparator;

/**
 * @author Artem Sidorov
 *
 * The QuickSort class provides static methods for sorting a list using the QuickSort algorithm.
 */
public class QuickSort {

    /**
     * Sorts the specified list using the QuickSort algorithm.
     *
     * @param list a list to sort
     * @param c comparator for comparing list items
     * @param <T> type of list items
     */
    public static <T> void sort(ArrList<T> list, Comparator<? super T> c) {
        quickSort(list, 0, list.size() - 1, c);
    }

    private static <T> void quickSort(ArrList<T> list, int low, int high, Comparator<? super T> c) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, c);
            quickSort(list, low, pivotIndex - 1, c);
            quickSort(list, pivotIndex + 1, high, c);
        }
    }

    private static <T> int partition(ArrList<T> list, int low, int high, Comparator<? super T> c) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (c.compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(ArrList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
