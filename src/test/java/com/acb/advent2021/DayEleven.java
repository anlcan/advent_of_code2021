package com.acb.advent2021;

import com.acb.Util;
import com.acb.aux.Point;
import com.acb.dayEleven.Dumbo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 09.01.22.
 */
public class DayEleven {

    @Test
    public void simpleTest() {
        /*
        11111
        19991
        19191
        19991
        11111
         */
        assertEquals(8, new Point(1,1).adjacentFull().size());
        final List<String> strings0 = List.of("99", "91");
        final Dumbo.Octopi oct0 = Dumbo.Octopi.of(strings0);
        assertEquals(3, oct0.stepOf(1));

        final List<String> strings = List.of("11111", "19991","19191", "19991", "11111");
        final Dumbo.Octopi oct = Dumbo.Octopi.of(strings);
        assertEquals(9, oct.stepOf(1));
        assertEquals(9, oct.stepOf(1));

    }

    @Test
    public void sampleTest() {
        final List<String> strings = Util.readStrings("/day11/sample.txt");
        final Dumbo.Octopi oct = Dumbo.Octopi.of(strings);
        assertEquals(204, oct.stepOf(10));
        assertEquals(1656, oct.stepOf(90));
    }

    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day11/input1.txt");
        final Dumbo.Octopi oct = Dumbo.Octopi.of(strings);

        assertEquals(1632, oct.stepOf(100));
    }
}
