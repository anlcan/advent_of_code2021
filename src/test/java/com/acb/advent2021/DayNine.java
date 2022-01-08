package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayNine.Lava;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 08.01.22.
 */
public class DayNine {

    @Test
    public void sampleTest() {
        final List<String> strings = Util.readStrings("/day9/sample.txt");
        Lava a1 = Lava.of(strings);
        assertEquals(15, a1.riskLevel());
    }

    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day9/input1.txt");
        Lava a1 = Lava.of(strings);
        assertEquals(504, a1.riskLevel());
    }

}
