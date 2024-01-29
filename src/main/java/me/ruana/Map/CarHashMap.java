package me.ruana.Map;

import me.ruana.Car;
import me.ruana.CarHashSet.CarHashSet;

import java.util.*;

public class CarHashMap <K, V> implements CarMap <K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    @Override
    public void put(K key, V value) {
        if (size >= array.length*0.75) {
            increaseArray();
        }
        boolean added = add(key, value, array);
        if (added) {
            size++;
        }
    }
    private boolean add(K k, V v, Entry[] dst) {
        int position = getElementPosition(k, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(k, v, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.key.equals(k)) {
                    existedElement.value = v;
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new Entry(k, v, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    private int getElementPosition(K k, int arrayLength) {
        return Math.abs(k.hashCode() % arrayLength);
    }

    @Override
    public V get(K k) {
        int position = getElementPosition(k, array.length);

//        if (array[position] == null) {
//            throw new NoSuchElementException();
//        } else {
//            Entry existElement = array[position];
//            while (true) {
//                if (existElement != null && existElement.key.equals(k)) {
//                    return existElement.value;
//                } else if (existElement != null && !existElement.key.equals(k)) {
//                    existElement = existElement.next;
//                    return existElement.value;
//                }
//            }
//        }
        Entry existedElement = array[position];
        while (existedElement != null) {
            if (existedElement.key.equals(k)) {
                return (V)existedElement.value;
            }
            existedElement = existedElement.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Set<K> keySet() {
        Set<K> carOwnerSet = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Entry element =  array[i];
                Entry nextElement = element.next;
                carOwnerSet.add((K)element.key);

                while (nextElement != null) {
                    element = nextElement;
                    carOwnerSet.add((K)element.key);
                    nextElement = element.next;

                }

            }
        }
        return carOwnerSet;
    }


    @Override
    public List<V> values() {
        List<V> carList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Entry element = array[i];
                Entry nextElement = element.next;
                carList.add((V)element.value);

                while (nextElement != null) {
                    element = nextElement;
                    carList.add((V)element.value);
                    nextElement = element.next;
                }
            }
        }
        return carList;
    }

    @Override
    public boolean remove(K k) {
        int position = getElementPosition(k, array.length);
        Entry existedElement = array[position];

        if (existedElement != null && existedElement.key.equals(k)) {
            array[position] = existedElement.next;
            size--;
            return true;

        } else {
            while (existedElement != null) {
                Entry nextElement = existedElement.next;
                if (nextElement == null) {
                    return false;
                } else {
                    if (nextElement.key.equals(k)) {
                        existedElement.next = nextElement.next;
                        size--;
                        return true;
                    }
                    existedElement = existedElement.next;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                add((K)existedElement.key, (V)existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;

    }

    private static class Entry <K, V> {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public void setValue(V value) {
            this.value = value;
        }


    }
}
