package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayTwo.Submarine;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 18.12.21.
 */
public class DayTwo {


    @Test
    void simpleIncrease() {

        final List<Submarine.Command> integers = Util.readStrings("/day2/sample.txt").stream()
                .map(Submarine.Command::parseString)
                .collect(Collectors.toList());

        assertEquals(900, new Submarine().applyCourse(integers).courseData());
//        assertEquals(150, new Submarine().applyCourse(integers).courseData());
    }

    @Test
    void inputOneTest() {

        final List<Submarine.Command> integers = Util.readStrings("/day2/input1.txt").stream()
                .map(Submarine.Command::parseString)
                .collect(Collectors.toList());

//        assertEquals(1893605, new Submarine().applyCourse(integers).courseData());
        assertEquals(2120734350, new Submarine().applyCourse(integers).courseData());
    }

}
