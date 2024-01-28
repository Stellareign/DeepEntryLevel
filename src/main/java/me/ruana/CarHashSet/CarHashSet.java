package me.ruana.CarHashSet;

import me.ruana.Car;
import me.ruana.CarCollections;

import java.util.Iterator;

public class CarHashSet implements CarSet, CarCollections {
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    @Override
    public boolean add(Car car) {
        if (size >= array.length * 0.75) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }


    private boolean add(Car car, Entry[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(car, null);
            dst[position] = entry;
//            size++;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.value.equals(car)) {
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new Entry(car, null);
//                    size++;
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }


    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        while (last != null) {
            if (secondLast.value.equals(car)) {
                array[position] = last;
                size--;
                return true;
            }
            if (last.value.equals(car)) {
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Car car) {
        boolean isExist = false;
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            isExist = false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        while (last != null) {
            if (secondLast.value.equals(car)) {
                isExist = true;
            }
            if (last.value.equals(car)) {
                isExist = true;
            } else {
                last = last.next;
            }
        }
        return isExist;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[16];
        size = 0;

    }

    private int getElementPosition(Car car, int arrayLength) {
        return Math.abs(car.hashCode() % arrayLength);
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                add(existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            int index = 0;
            int arrayIndex = 0;
            Entry entry;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Car next() {
                while ((array[arrayIndex] == null)){
                arrayIndex++;
            }
                if (entry == null) {
                    entry = array[arrayIndex];
                }
                Car result = entry.value;
                entry = entry.next;
                if (entry == null){
                    arrayIndex++;
                }
                index++;
                return result;
            }
        };
    }

    //***************************************************************************************
    private static class Entry {
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
