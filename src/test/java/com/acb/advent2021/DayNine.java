package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayNine.Lava;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 08.01.22.
 */
public class DayNine {

    @Test()
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void sampleTest() {
        final List<String> strings = Util.readStrings("/day9/sample.txt");
        Lava a1 = Lava.of(strings);
        assertEquals(15, a1.riskLevel());
        assertEquals(1134, a1.big3size());
    }

    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day9/input1.txt");
        Lava a1 = Lava.of(strings);
        assertEquals(504, a1.riskLevel());
        assertEquals(1558722, a1.big3size());
    }

}
