package com.acb.advent2021;

import com.acb.Util;
import com.acb.daySeven.Allign;
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
        assertEquals(37, a1.bestLinear());
        assertEquals(2, a1.getBestLinearCostLine());
        // position 1 (41 fuel), position 3 (39 fuel), or position 10 (71 fuel).
        assertEquals(41, a1.linearCostAt(1));
        assertEquals(39, a1.linearCostAt(3));
        assertEquals(71, a1.linearCostAt(10));

        assertEquals(206, a1.incCostAt(2));
        assertEquals(168, a1.bestInc());

    }

    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day7/input1.txt");
        Allign a1 = Allign.of(strings.get(0));
        assertEquals(347011, a1.bestLinear());
        assertEquals(98363777, a1.bestInc());
    }

}
