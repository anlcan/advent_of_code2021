package com.acb.advent2021;

import com.acb.Util;
import daySeven.Allign;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 08.01.22.
 */
public class DaySeven {

    @Test
    public void sampleTest(){
        final List<String> strings = Util.readStrings("/day7/sample.txt");
        Allign a1 =  Allign.of(strings.get(0));
        assertEquals(37, a1.best());
        assertEquals(2, a1.getBestLine());
        // position 1 (41 fuel), position 3 (39 fuel), or position 10 (71 fuel).
        assertEquals(41, a1.costAt(1));
        assertEquals(39, a1.costAt(3));
        assertEquals(71, a1.costAt(10));

    }

    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day7/input1.txt");
        Allign a1 = Allign.of(strings.get(0));
        assertEquals(37, a1.best());
    }

}
