package com.acb.dayOne;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created on 17.12.21.
 */
public class Sonar {

    public static int countIncrement(final List<Integer> input){
        Integer previous = input.get(0);
        int counter = 0;
        for (Integer i : input) {

            if (input.get(i) > previous) {
                counter++;
            }
            previous = i;
        }
        return counter;
    }

    public static int countSlidingIncrement(final List<Integer> input){

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int counter = 0;
        for (final Integer forth : input) {
            if (stack.size() < 3) {
                stack.push(forth);
            }
            int sum1 = stack.stream().mapToInt(Integer::intValue).sum();
            stack.removeFirst();
            stack.addLast(forth);
            int sum2 = stack.stream().mapToInt(Integer::intValue).sum();
            if (sum2 > sum1) {
                counter++;
            }

        }
        return counter;
    }



}
