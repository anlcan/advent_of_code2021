package com.acb.dayOne;

import java.util.List;

/**
 * Created on 17.12.21.
 */
public class Sonar {

    public static int countIncrement(final List<Integer> input){
        Integer previous = input.get(0);
        int counter = 0;
        for (int i = 1; i < input.size() ; i++) {

            if (input.get(i) > previous) {
                previous = input.get(i);
                counter++;
            }
            previous = input.get(i);
        }
        return counter;
    }


}
