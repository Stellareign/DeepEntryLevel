package me.ruana;

import me.ruana.CarHashSet.CarHashSet;
import me.ruana.CarHashSet.CarSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarSetTest {
    private CarSet carSet;


    @Before
    public void setUp() throws Exception {
        carSet = new CarHashSet();
        for (int i = 0; i < 100; i++) {
            carSet.add(new Car("Brand" + i, i));
        }
    }


    @Test
    public void whenAdd100ElementsMustBe100_getSize() {
        assertEquals(100, carSet.size());
    }

    @Test
    public void whenAddNewElementsMustReturnTrue_methodAdd() {
        Car car = new Car("Brand101", 101);
        assertTrue(carSet.add(car));
    }

    @Test
    public void whenAddSameElementsMustReturnFalse_methodAdd() {
        assertEquals(100, carSet.size());
        assertTrue(carSet.add(new Car("BMW", 5)));
        assertFalse(carSet.add(new Car("BMW", 5)));
        assertFalse(carSet.add(new Car("BMW", 5)));
        assertEquals(101, carSet.size());
    }


    @Test
    public void whenElementRemoveThenSizeMustBeDecreased_methodRemove() {

        assertTrue(carSet.remove(new Car("Brand11", 11)));
        assertEquals(99, carSet.size());

    }

    @Test
    public void whenNonExistingElementRemovedThenReturnFalse_methodRemove() {
        Car car = new Car("KIA", 15);
        assertFalse(carSet.remove(car));
        assertEquals(100, carSet.size());
    }

    @Test
    public void whenSetClearThenSizeMustBe0_methodClear() {
        carSet.clear();
        assertEquals(0, carSet.size());
    }

    @Test
    public void contains(){
        assertTrue(carSet.contains(new Car("Brand1", 1)));
        assertFalse(carSet.contains(new Car("Toyota", 46)));
    }

}