package com.acb.advent2021;

import com.acb.Util;
import daySix.Lantern;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 08.01.22.
 */
public class DaySix {

    @Test
    public void testSample(){
        final Lantern.Swarm s1 = Lantern.Swarm.of("3,4,3,1,2");
        assertEquals(26, s1.tick(18));
        assertEquals(5934, s1.tick(80-18));
    }

    @Test
    public void inputOneTest(){
        final List<String> strings = Util.readStrings("/day6/sample.txt");
        final Lantern.Swarm s1 = Lantern.Swarm.of(strings.get(0));
        assertEquals(26, s1.tick(80));
    }
}
