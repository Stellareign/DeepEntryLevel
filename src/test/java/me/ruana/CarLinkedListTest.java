package me.ruana;

import me.ruana.lists.CarArrayList;
import me.ruana.lists.CarLinkedList;
import me.ruana.lists.CarList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarLinkedListTest {
    private CarList carList;


    @Before
    public void setUp() throws Exception {

        carList = new CarLinkedList();
        for (int i = 0; i < 100; i++) {
            carList.add(new Car("Brand" + i, i));
        }
    }


    @Test
    public void whenAdd100ElementsMustBe100() {
        assertEquals(100, carList.size());
    }

    @Test
    public void whenElementRemoveByIndexThenSizeMustBeDecreased() {
        assertTrue(carList.removeAt(5));
        assertEquals(99, carList.size());
    }

    @Test
    public void whenElementRemoveThenSizeMustBeDecreased() {
        Car car = new Car("KIA", 15);
        carList.add(car);
        assertEquals(101, carList.size());
        assertTrue(carList.remove(car));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenNonExistingElementRemovedThenReturnFalse() {
        Car car = new Car("KIA", 15);
        assertFalse(carList.remove(car));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenListClearThenSizeMustBe0() {
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsThenThrownException() {
        carList.get(100);
    }

    @Test
    public void methodGtReturnRightValue() {
        Car car = carList.get(0);
        assertEquals("Brand0", car.getBrand());
    }

    @Test
    public void whenAddCarByIndexThenSizeMustBe101() {
        Car car = new Car("KIA", 2);
        carList.add(car, 50);
        Car car1 = carList.get(50);
        assertEquals("KIA", car1.getBrand());
        assertEquals(101, carList.size());
    }

    @Test
    public void whenAddCarByIndexToFirstPositionThenSizeMustBe101() {
        Car car = new Car("KIA", 2);
        carList.add(car, 0);
        Car car1 = carList.get(0);
        assertEquals("KIA", car1.getBrand());
        assertEquals(101, carList.size());
    }

    @Test
    public void whenAddCarByIndexToLastPositionThenSizeMustBe101() {
        Car car = new Car("KIA", 2);
        carList.add(car, 100);
        Car car1 = carList.get(100);
        assertEquals("KIA", car1.getBrand());
        assertEquals(101, carList.size());
    }
    @Test
    public void contains(){
        assertTrue(carList.contains(new Car("Brand1", 1)));
        assertFalse(carList.contains(new Car("Toyota", 46)));
    }

}