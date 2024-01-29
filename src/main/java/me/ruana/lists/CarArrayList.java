package me.ruana.lists;

import me.ruana.Car;
import me.ruana.CarCollections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class CarArrayList <T> implements CarList <T>{
    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)array[index];
    }

    @Override
    public boolean add(T t) {
        increaseArray();
        array[size] = t;
        size++;
        return true;
    }

    @Override
    public boolean add(T t, int index) {
        if (index < 0 || index > size) { // здесь индекс может быть равен размеру
            throw new IndexOutOfBoundsException();
        }
        size++;
        increaseArray();
        System.arraycopy(array, index, array, index+1, size-index); // перемещение элементов массива на один шаг с сохранением значение[под индексом]

//        for (int i = size; i > index; i--) {
//            array[i] = array[i - 1];
//        }
        array[index] = t; // и вставка значения по индексу
        return true;
    }

    @Override
    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(t)) {
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
        array = new Object[10];
        size = 0;
    }

    @Override
    public boolean contains(T t) {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(t)) {
               isExist= true;
            }
        } return isExist;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T)array[index++];
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
