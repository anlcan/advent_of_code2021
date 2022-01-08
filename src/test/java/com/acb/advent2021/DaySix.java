package com.acb.advent2021;

import com.acb.Util;
import com.acb.daySix.Lantern;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 08.01.22.
 */
public class DaySix {


    @Test
    public void testSample(){
        final Lantern.SwarmTracker s1 = Lantern.SwarmTracker.of("3,4,3,1,2");
//        assertEquals(5, s1.tick());
        assertEquals(26, s1.tick(18));
        assertEquals(5934, s1.tick(80-18));
        assertEquals(191336, s1.tick(120-80));
        assertEquals(26984457539l, s1.tick(256-120));

    }

    @Test
    public void sampleTest(){
        final List<String> strings = Util.readStrings("/day6/sample.txt");
        final Lantern.SwarmTracker s1 = Lantern.SwarmTracker.of(strings.get(0));
        assertEquals(380612, s1.tick(80));

    }


    @Test
    public void inputOneTest(){
        final List<String> strings = Util.readStrings("/day6/input2.txt");
        final Lantern.SwarmTracker s1 = Lantern.SwarmTracker.of(strings.get(0));
//        assertEquals(380612, s1.tick(80));
        assertEquals(1710166656900l, s1.tick(256));
    }
}
