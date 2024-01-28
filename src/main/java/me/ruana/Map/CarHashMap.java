package me.ruana.Map;

import me.ruana.Car;
import me.ruana.CarHashSet.CarHashSet;

import java.util.*;

public class CarHashMap implements CarMap {
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= array.length*0.75) {
            increaseArray();
        }
        boolean added = add(key, value, array);
        if (added) {
            size++;
        }
    }
    private boolean add(CarOwner carOwner, Car car, Entry[] dst) {
        int position = getElementPosition(carOwner, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(carOwner, car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.key.equals(carOwner)) {
                    existedElement.value = car;
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new Entry(carOwner, car, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    private int getElementPosition(CarOwner carOwner, int arrayLength) {
        return Math.abs(carOwner.hashCode() % arrayLength);
    }

    @Override
    public Car get(CarOwner carOwner) {
        int position = getElementPosition(carOwner, array.length);

//        if (array[position] == null) {
//            throw new NoSuchElementException();
//        } else {
//            Entry existElement = array[position];
//            while (true) {
//                if (existElement != null && existElement.key.equals(carOwner)) {
//                    return existElement.value;
//                } else if (existElement != null && !existElement.key.equals(carOwner)) {
//                    existElement = existElement.next;
//                    return existElement.value;
//                }
//            }
//        }
        Entry existedElement = array[position];
        while (existedElement != null) {
            if (existedElement.key.equals(carOwner)) {
                return existedElement.value;
            }
            existedElement = existedElement.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> carOwnerSet = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Entry element = array[i];
                Entry nextElement = element.next;
                carOwnerSet.add(element.key);

                while (nextElement != null) {
                    element = nextElement;
                    carOwnerSet.add(element.key);
                    nextElement = element.next;

                }

            }
        }
        return carOwnerSet;
    }


    @Override
    public List<Car> values() {
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                Entry element = array[i];
                Entry nextElement = element.next;
                carList.add(element.value);

                while (nextElement != null) {
                    element = nextElement;
                    carList.add(element.value);
                    nextElement = element.next;
                }
            }
        }
        return carList;
    }

    @Override
    public boolean remove(CarOwner carOwner) {
        int position = getElementPosition(carOwner, array.length);
        Entry existedElement = array[position];

        if (existedElement != null && existedElement.key.equals(carOwner)) {
            array[position] = existedElement.next;
            size--;
            return true;

        } else {
            while (existedElement != null) {
                Entry nextElement = existedElement.next;
                if (nextElement == null) {
                    return false;
                } else {
                    if (nextElement.key.equals(carOwner)) {
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
                add(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;

    }

    private static class Entry {
        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public void setValue(Car value) {
            this.value = value;
        }


    }
}
