package me.ruana.queue;

import me.ruana.Car;
import me.ruana.CarCollections;

public interface CarQueue <T> extends CarCollections <T>{
    boolean add(T car);
    T peek();

    T poll();
}
