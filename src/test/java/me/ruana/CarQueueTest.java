package me.ruana;

import me.ruana.lists.CarLinkedList;
import me.ruana.queue.CarQueue;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CarQueueTest {
    private CarQueue carQueue;

    @Before
    public void setUp() throws Exception {
        carQueue = new CarLinkedList();
        for (int i = 0; i < 10; i++) {
            carQueue.add(new Car("Brand" + i, i));
        }
        // init
    }


    @Test
    public void add() {
        assertEquals(10, carQueue.size());

    }

    @Test
    public void peek() {

        assertEquals(new Car("Brand0", 0), carQueue.peek());
        assertEquals(10, carQueue.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void peekException() {
        carQueue.clear();
        carQueue.peek();
    }

    @Test
    public void poll() {
        Car car = carQueue.poll();
        assertEquals(new Car("Brand0", 0), car);
        assertEquals(9, carQueue.size());
        assertEquals(new Car("Brand1", 1), carQueue.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void pollException() {
        carQueue.clear();
        carQueue.poll();
    }
}