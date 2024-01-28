package me.ruana.queue;

import me.ruana.Car;
import me.ruana.CarCollections;

public interface CarQueue extends CarCollections {
    boolean add(Car car);
    Car peek();

    Car poll();
}
