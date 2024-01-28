package me.ruana.lists;

import me.ruana.Car;
import me.ruana.CarCollections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class CarArrayList implements CarList {
    private Car[] array = new Car[10];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean add(Car car) {
        increaseArray();
        array[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        if (index < 0 || index > size) { // здесь индекс может быть равен размеру
            throw new IndexOutOfBoundsException();
        }
        size++;
        increaseArray();
        System.arraycopy(array, index, array, index+1, size-index); // перемещение элементов массива на один шаг с сохранением значение[под индексом]

//        for (int i = size; i > index; i--) {
//            array[i] = array[i - 1];
//        }
        array[index] = car; // и вставка значения по индексу
        return true;
    }

    @Override
    public boolean remove(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        IntStream.range(index, size - 1).forEachOrdered(i -> array[i] = array[i + 1]);
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Car[10];
        size = 0;
    }

    @Override
    public boolean contains(Car car) {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
               isExist= true;
            }
        } return isExist;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Car next() {
                return array[index++];
            }
        };
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void increaseArray() {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

}
