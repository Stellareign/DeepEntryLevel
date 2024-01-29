package me.ruana.lists;

import me.ruana.Car;
import me.ruana.CarCollections;
import me.ruana.queue.CarQueue;
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CarLinkedList<T> implements CarList<T>, CarQueue<T> {
    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public T get(int index) {
        return (T)getNode(index).value;
    }

    @Override
    public boolean add(T t) {
        if (size == 0) {
//            Node node = new Node(null, t, null);
//            first = node;
//            last = node;
            first = new Node(null, t, null);
            last = first;

        } else {
            Node secondLast = last;
            last = new Node(secondLast, t, null);
            secondLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(T t, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(t);
        }
        Node nodeNext = getNode(index);
        Node nodePrivious = nodeNext.previous;
        Node newNode = new Node(nodePrivious, t, nodeNext);
        nodeNext.previous = newNode;
        if (nodePrivious != null) {
            nodePrivious.next = newNode;
        } else {
            first = newNode;
        }
        size++;
        return true;
    }

    private Node getNode(int index) {
        checkIndex(index);

        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    @Override
    public boolean remove(T t) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(t)) {
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node node = getNode(index);
        Node nodeNext = node.next;
        Node nodePrevious = node.previous;
        if (nodeNext != null) {
            nodeNext.previous = nodePrevious;
        } else {
            last = nodePrevious;
        }
        if (nodePrevious != null) {
            nodePrevious.next = nodeNext;
        } else {
            first = nodeNext;
        }
        size--;
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(T t) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(t)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private CarLinkedList.Node node = first;

            @Override
            public boolean hasNext() {

                return node != null;

            }

            @Override
            public T next() {
                T t = (T) node.value;
                node = node.next;
                return t;
            }
        };
    }

    // *************************** FOR QUEUE ****************************
    @Override
    public T peek() {
        if (size > 0) {
            return (T) get(0);
        } else {
            throw new NoSuchElementException();
        }
    }

//    @Override
//    public Car peek() {
//       return size > 0 ? get(0) : null;
//
//    }

    @Override
    public T poll() {
        if (size > 0) {
            T t = (T) get(0);
            removeAt(0);
            return t;
        } else throw new NoSuchElementException();
    }

    private static class Node<V> {
        private Node previous;
        private V value;
        private Node next;

        public Node(Node previous, V value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
