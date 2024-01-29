package me.ruana.lists;

import me.ruana.Car;
import me.ruana.CarCollections;

public interface CarList <T> extends CarCollections <T>{
    T get(int index);
    boolean add(T car, int index);
    boolean removeAt(int index);


}
