package me.ruana;

import me.ruana.lists.CarArrayList;
import me.ruana.lists.CarList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Car car1 = new Car("KIA", 1);
//        Car car2 = new Car("KIA", 1);
//        System.out.println(car2.equals(car1));
//        System.out.println(car2.hashCode());
//        System.out.println(car1.hashCode());

//        CarCollections cars = new CarArrayList();
//        for (int i = 0; i < 10; i++) {
//            cars.add(new Car("Band" + i, i));
//        }
//        for (Car car : cars) {
//            System.out.println(car);
//        }
        /**
         * вариант 1 использования компаратора для сортировки чисел
         */
//        Set<Integer> numbers = new TreeSet<>(Comparator.reverseOrder());
        Set<Integer> numbers = new TreeSet<>(new Comparator<Integer>() {
            @Override
            /**
             * вариант 2 использования компаратора
             */
//            public int compare(Integer o1, Integer o2) {
//                return o2.compareTo(o1);
//            }
            /**
             * или:
             */
//            public int compare(Integer o1, Integer o2) {
//                -return o1.compareTo(o2);
//            }

            /**
             * вариант 3: написать вручную.
             */
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;

                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            numbers.add((int) (Math.random() * 10));
        }
        for (int number : numbers) {
            System.out.println(number);
        }

    }

}