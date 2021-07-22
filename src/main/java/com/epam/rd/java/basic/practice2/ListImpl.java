package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node firstElement;

    private Node lastElement;

    private int size = 0;

    public ListImpl() {
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
    public void addFirst(Object element) {
        Node node = new Node(element, firstElement);
        firstElement = node;
        if (lastElement == null) {
            lastElement = node;
        }
        ++size;
    }

    @Override
    public void addLast(Object element) {
        if (firstElement == null) {
            addFirst(element);
        } else {
            Node node = new Node(element, null);
            lastElement.next = node;
            lastElement = node;
            ++size;
        }
    }

    @Override
    public void removeFirst() {
        if (size > 0) {
            if (size == 1) {
                firstElement = null;
                lastElement = null;
            } else {
                firstElement = firstElement.next;
            }
            --size;
        }
    }

    @Override
    public void removeLast() {
        if (size > 0) {
            if (size == 1) {
                firstElement = null;
                lastElement = null;
            } else {
                Node localFirstElement = firstElement;
                while (localFirstElement != null) {
                    if (localFirstElement.next == lastElement) {
                        localFirstElement.next = null;
                        lastElement = localFirstElement;
                    }
                    localFirstElement = localFirstElement.next;
                }
                --size;
            }
        }
    }

    @Override
    public Object getFirst() {
        if (firstElement == null) {
            return null;
        }
        return firstElement.data;
    }

    @Override
    public Object getLast() {
        if (lastElement == null) {
            return null;
        }
        return lastElement.data;
    }

    @Override
    public Object search(Object element) {
        Node localFirstElement = firstElement;
        while (localFirstElement != null) {
            if (element.equals(localFirstElement.data)) {
                return element;
            }
            localFirstElement = localFirstElement.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        try {
            Node localFirstElement = firstElement;
            if (element == localFirstElement.data) {
                firstElement = localFirstElement.next;
                --size;
                return true;
            }
            while (localFirstElement != null) {
                if (element == localFirstElement.next.data) {
                    localFirstElement.next = localFirstElement.next.next;
                    if (localFirstElement.next == null) {
                        lastElement = localFirstElement;
                    }
                    --size;
                    return true;
                }
                localFirstElement = localFirstElement.next;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
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
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast(null);
        list.addFirst("C");
        System.out.println(list);
        list.remove(null);
        System.out.println(list.getLast());
        System.out.println(list.getFirst());
        System.out.println(list);
    }
}
