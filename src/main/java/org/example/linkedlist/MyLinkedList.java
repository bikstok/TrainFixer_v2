package org.example.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    public int size() {
        return size;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;
            @Override
            public boolean hasNext() { return current != null; }
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
