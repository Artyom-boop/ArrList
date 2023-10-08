package org.example;

import java.util.Arrays;

/**
 * @author Artem Sidorov
 * The Arraylist class is an implementation of a dynamic array (ArrayList).
 * It provides basic operations of adding, deleting, accessing and manipulating array elements.
 *
 * @param <E> the type of items stored in the list
 */
public class ArrList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    Object[] elementData;

    /**
     * Creates an empty list with the default initial capacity (10 items).
     */
    public ArrList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates an empty list with the specified initial capacity.
     *
     * @param capacity initial capacity of the list
     */
    public ArrList(int capacity) {
        this.elementData = new Object[capacity];
    }

    /**
     * Returns the number of items in the list.
     *
     * @return number of items in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns an array containing all the elements of the list.
     *
     * @return array of list items
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Adds an item to the end of the list.
     *
     * @param o item to add
     * @return true if the element was successfully added, otherwise false
     */
    public boolean add(E o) {
        if (elementData.length > size + 1) {
            elementData[size++] = o;
            return true;
        }
        expansionElementData();
        elementData[size++] = o;
        return true;
    }

    /**
     * Deletes the first occurrence of the specified item from the list.
     *
     * @param o item to delete
     * @return true if the element was successfully deleted, otherwise false
     */
    public boolean remove(E o) {
        int indexObj =  indexOf(o);
        if (indexObj >= 0) {
            for (int i = indexObj; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            elementData[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Checks whether the list contains the specified element.
     *
     * @param o element to search for
     * @return true if the element is found, otherwise false
     */
    public boolean contains(E o) {
        return indexOf(o) >= 0;
    }

    /**
     * Очищает список, устанавливая его в начальное состояние.
     */
    public void clear() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Returns an element at the specified index.
     *
     * @param index element index
     * @return the element stored at the specified index
     * @throws IndexOutOfBoundsException if the index goes beyond the size of the list
     */
    public E get(int index) {
        rangeCheck(index);
        @SuppressWarnings("unchecked")
        E element = (E) elementData[index];
        return element;
    }

    /**
     * Replaces an item in the list at the specified index with a new item and returns the old item.
     *
     * @param index index of the element to replace
     * @param element new element
     * @return the old element that was replaced
     * @throws IndexOutOfBoundsException if the index goes beyond the size of the list
     */
    public E set(int index, E element) {
        rangeCheck(index);
        @SuppressWarnings("unchecked")
        E oldElement = (E) elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    /**
     * Adds an element at the specified index by shifting existing elements to the right.
     *
     * @param index the index to add the element to
     * @param element element to add
     * @throws IndexOutOfBoundsException if the index goes beyond the size of the list
     */
    public void add(int index, E element) {
        rangeCheck(index);
        if (elementData.length <= size + 1) {
            expansionElementData();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }

    /**
     * Removes an item from the list at the specified index and returns it.
     *
     * @param index index of the item to delete
     * @return deleted item
     * @throws IndexOutOfBoundsException if the index goes beyond the size of the list
     */
    public E remove(int index) {
        rangeCheck(index);
        @SuppressWarnings("unchecked")
        E element = (E) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return element;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list.
     *
     * @param o element to search for
     * @return the index of the element if it is found, otherwise -1
     */
    public int indexOf(E o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i]))
                return i;
        }
        return -1;
    }

    private void expansionElementData() {
        elementData = Arrays.copyOf(elementData, elementData.length * 2);
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return "ArrList{" +
                "size=" + size +
                ", elementData=" + Arrays.toString(toArray()) +
                '}';
    }
}
