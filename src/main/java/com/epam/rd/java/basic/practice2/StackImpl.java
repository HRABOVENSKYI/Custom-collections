package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private Node head;

    private int size = 0;

    public StackImpl() {
        // why not empty
    }

    private static class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(head);
    }

    private class IteratorImpl implements Iterator<Object> {

        Node node;

        public IteratorImpl(Node head) {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object element = node.data;
            node = node.next;
            return element;
        }
    }

    @Override
    public void push(Object element) {
        Node node = new Node(element, head);
        head = node;
        ++size;
    }

    @Override
    public Object pop() {
        if (size != 0) {
            Object removed = head.data;
            head = head.next;
            --size;
            return removed;
        }
        return null;
    }

    @Override
    public Object top() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    @Override
    public String toString() {
        if (size == 0 || head == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node localFirstElement = head;
        int iter = 0;
        Object[] arr = new Object[size];
        while (localFirstElement != null) {
            arr[iter] = localFirstElement.data;
            localFirstElement = localFirstElement.next;
            iter++;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            stringBuilder.append(arr[i]);
            if (i != 0) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.append("]").toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top());
        System.out.println(stack);
        stack.pop();
    }

}
