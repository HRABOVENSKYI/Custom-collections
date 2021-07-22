package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Node firstElement;

    private Node lastElement;

    private int size = 0;

    public QueueImpl() {
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
        firstElement = null;
        lastElement = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(firstElement);
    }

    private class IteratorImpl implements Iterator<Object> {

        Node node;

        public IteratorImpl(Node firstElement) {
            this.node = firstElement;
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
    public void enqueue(Object element) {
        Node node = new Node(element, null);
        if (lastElement == null) {
            lastElement = node;
            firstElement = lastElement;
        } else {
            lastElement.next = node;
            lastElement = node;
        }
        ++size;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {
            return null;
        }
        Object removed = firstElement.data;
        firstElement = firstElement.next;
        --size;
        return removed;
    }

    @Override
    public Object top() {
        if (firstElement != null) {
            return firstElement.data;
        }
        return null;
    }

    @Override
    public String toString() {
        if (size == 0 || firstElement == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node localFirstElement = firstElement;
        int iter = 1;
        while (localFirstElement != null) {
            stringBuilder.append(localFirstElement.data);
            if (iter != size) {
                stringBuilder.append(", ");
            }
            localFirstElement = localFirstElement.next;
            ++iter;
        }

        return stringBuilder.append("]").toString();
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();

        queue.dequeue();
        System.out.println(queue.top());
        System.out.println(queue);
    }

}
