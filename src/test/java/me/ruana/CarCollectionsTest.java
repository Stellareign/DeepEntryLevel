package me.ruana;

import me.ruana.CarHashSet.CarHashSet;
import me.ruana.lists.CarArrayList;
import me.ruana.lists.CarLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarCollectionsTest {
    private CarCollections collections;

    @Before
    public void setUp() throws Exception {

//        collections = new CarLinkedList();
        collections = new CarHashSet();
        for (int i = 0; i < 100; i++) {
            collections.add(new Car("Brand" + i, i));
        }
    }

    @Test
    public void testForeach() {
        int index = 0;
        for (Car car : collections) {
            index++;
        }
        assertEquals(100, index);
    }

}