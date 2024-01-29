package me.ruana;

import me.ruana.Map.CarMap;
import me.ruana.Map.CarHashMap;
import me.ruana.Map.CarOwner;
import org.junit.Before;
import org.junit.Test;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CarMapTest {
    private CarMap carMap;

    @Before
    public void setUp() throws Exception {
        carMap = new CarHashMap<>();

    }

    @Test
    public void whenPut100ElementsMustBe100_getSize() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i + 1, "Ivan" + (i + 1), "Ivanov" + (i + 1));
            Car car = new Car("Brand" + i, i);

            carMap.put(carOwner, car);

        }
        assertEquals(100, carMap.size());

    }

    @Test
    public void whenPut100ElementsWhith10DifferentKeysMustBe100_getSize() {

        for (int i = 0; i < 100; i++) {
            int ind = i % 10;
            CarOwner carOwner = new CarOwner(ind, "Ivan" + ind, "Ivanov" + ind);
            Car car = new Car("Brand" + ind, ind);
            carMap.put(carOwner, car);

        }
        assertEquals(10, carMap.size());

    }


    @Test
    public void whenGetByOwnerReturnCar() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i + 1, "Ivan" + (i + 1), "Ivanov" + (i + 1));
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        CarOwner key = new CarOwner(12, "Ivan12", "Ivanov12");
        Car car = new Car("Brand11", 11);
        assertEquals(car, carMap.get(key));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetByOwnerNotExistReturnException() {
        CarOwner key = new CarOwner(111, "Ivan122", "Ivanov122");
        carMap.get(key);
    }

    @Test
    public void keySet() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i + 1, "Ivan" + (i + 1), "Ivanov" + (i + 1));
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        Set<CarOwner> carOwnerSet = carMap.keySet();
        assertEquals(100, carOwnerSet.size());
    }

    @Test
    public void values() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i + 1, "Ivan" + (i + 1), "Ivanov" + (i + 1));
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        CarOwner key = new CarOwner(12, "Ivan12", "Ivanov12");
        Car car = new Car("Brand11", 11);
        List<Car> carList = carMap.values();
        assertEquals(100, carList.size());
       
    }

    @Test
    public void removeOnlyOnce() {
        for (int i = 0; i < 10; i++) {
            CarOwner carOwner = new CarOwner(i + 1, "Ivan" + (i + 1), "Ivanov" + (i + 1));
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        assertEquals(10, carMap.size());

        CarOwner keyForDelete = new CarOwner(6, "Ivan6", "Ivanov6");

        assertTrue(carMap.remove(keyForDelete));
        assertEquals(9, carMap.size());
        assertFalse(carMap.remove(keyForDelete));
    }


    @Test
    public void clear() {
        assertEquals(0, carMap.size());
    }
}