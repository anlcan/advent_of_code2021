package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayTen.Parser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created on 08.01.22.
 */
public class DayTen {

    @Test
    public void simpleTest(){
        assertTrue(new Parser("()").isCompleted());
        assertTrue(new Parser("[<>({}){}[([])<>]]").isCompleted());
        assertTrue(new Parser("(((((((((())))))))))").isCompleted());
        assertTrue(new Parser("{()()()}").isCompleted());
        assertTrue(new Parser("<([{}])>").isCompleted());

    }

    @Test
    public void sampleTest() {
        final List<String> strings = Util.readStrings("/day10/sample.txt");
        assertEquals(26397, Parser.score(strings));
    }


    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day10/input1.txt");
        assertEquals(166191, Parser.score(strings));
    }
}
