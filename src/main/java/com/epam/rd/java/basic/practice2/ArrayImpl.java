package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private Object[] elementData;

    private int cursor;

    public ArrayImpl(int size) {
        this.cursor = 0;
        if (size > 0) {
            this.elementData = new Object[size];
        } else if (size == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
    }

    public ArrayImpl() {
        this.cursor = 0;
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    @Override
    public void clear() {
        elementData = EMPTY_ELEMENT_DATA;
        cursor = 0;
    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return elementData[position++];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }

    @Override
    public void add(Object element) {
        if (cursor == size()) {
            Object[] temp = new Object[elementData.length + 1];
            for (int i = 0; i < elementData.length; i++) {
                temp[i] = elementData[i];
            }
            elementData = temp;
        }
        elementData[cursor++] = element;
    }

    @Override
    public void set(int index, Object element) {
        elementData[index] = element;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (element.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (elementData != null && index > 0 && index <= elementData.length) {

            Object[] anotherArray = new Object[elementData.length - 1];

            int k = 0;

            for (int i = 0; i < elementData.length; i++) {
                if (i == index) {
                    continue;
                }
                anotherArray[k++] = elementData[i];
            }

            elementData = anotherArray;
        }
    }

    @Override
    public String toString() {
        if (elementData == null)
            return "null";
        int iMax = elementData.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(elementData[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void main(String[] args) {
        // why not empty
    }
}
