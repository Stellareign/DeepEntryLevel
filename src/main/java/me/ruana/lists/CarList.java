package me.ruana.lists;

import me.ruana.Car;
import me.ruana.CarCollections;

public interface CarList extends CarCollections {
    Car get(int index);
    boolean add(Car car, int index);
    boolean removeAt(int index);


}
