package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayTen.Parser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        final Parser p1 = new Parser("[({(<(())[]>[[{[]{<()<>>");
        assertFalse(p1.isCompleted());
        p1.complete();
        assertEquals("}}]])})]", p1.getCompString());

    }

    @Test
    public void sampleTest() {
        final List<String> strings = Util.readStrings("/day10/sample.txt");
        assertEquals(26397, Parser.getErrorScore(strings));
        assertEquals(288957, Parser.getCompletionScore(strings));
    }


    @Test
    public void inputOneTest() {
        final List<String> strings = Util.readStrings("/day10/input1.txt");
        assertEquals(166191, Parser.getErrorScore(strings));
        assertEquals(1152088313, Parser.getCompletionScore(strings));
    }
}
