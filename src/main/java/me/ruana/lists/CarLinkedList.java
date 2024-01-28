package me.ruana.lists;

import me.ruana.Car;
import me.ruana.CarCollections;
import org.w3c.dom.Node;

import java.util.Iterator;

public class CarLinkedList implements CarList {
    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public boolean add(Car car) {
        if (size == 0) {
//            Node node = new Node(null, car, null);
//            first = node;
//            last = node;
            first = new Node(null, car, null);
            last = first;

        } else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(car);
        }
        Node nodeNext = getNode(index);
        Node nodePrivious = nodeNext.previous;
        Node newNode = new Node(nodePrivious, car, nodeNext);
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
    public boolean remove(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
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
    public boolean contains(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            private CarLinkedList.Node node = first;

            @Override
            public boolean hasNext() {

                return node != null;

            }

            @Override
            public Car next() {
                Car car = node.value;
                node = node.next;
                return car;
            }
        };
    }

    private static class Node {
        private Node previous;
        private Car value;
        private Node next;

        public Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
