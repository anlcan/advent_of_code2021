package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayOne.Sonar;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 17.12.21.
 */

public class DayOne {

        @Test
        void simpleIncrease() {

            final List<Integer> integers = Util.readInteger("/day1/sample.txt");
            assertEquals(7,  Sonar.countIncrement(integers));
        }

    @Test
    void firtInput() {

        final List<Integer> integers = Util.readInteger("/day1/input.txt");
        System.out.println(Sonar.countIncrement(integers));
        //assertEquals(7,  Sonar.countIncrement(integers));
    }

}
