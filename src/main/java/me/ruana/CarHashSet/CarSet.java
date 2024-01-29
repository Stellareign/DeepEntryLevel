package me.ruana.CarHashSet;

import me.ruana.Car;
import me.ruana.CarCollections;

public interface CarSet <T> extends CarCollections <T>{

    boolean add(T car);

}
