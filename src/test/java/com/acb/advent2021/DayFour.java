package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayFour.Bingo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 18.12.21.
 */
public class DayFour {

    @Test
    void testSimpleCases(){
        final List<List<Integer>> test = List.of(List.of(1,2,3), List.of(4,5,6), List.of(7,8,9));


        Bingo.Card c = new Bingo.Card(test);
        System.out.println(c.horizontals);
        assertFalse(c.next(1));
        assertFalse(c.next(2));
        assertTrue(c.next(3));
        assertEquals(117, c.score());

        Bingo.Card c2 = new Bingo.Card(test);
        System.out.println(c.horizontals);
        assertFalse(c2.next(2));
        assertFalse(c2.next(5));
        assertFalse(c2.next(9));
        assertTrue(c2.next(8));
        assertEquals(168, c2.score());
    }

    @Test
    void testSample(){
        final List<String> lines = Util.readStringsDoubleNewline("/day4/sample.txt");
        Bingo bingo = new Bingo();
        IntStream.range(1, lines.size()).forEach(c-> bingo.addCard(lines.get(c)));

        final List<Integer> numbers = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::valueOf).boxed().toList();
        assertEquals(4512, bingo.play(numbers));
    }

    @Test
    void input1(){
        final List<String> lines = Util.readStringsDoubleNewline("/day4/input1.txt");
        Bingo bingo = new Bingo();
        IntStream.range(1, lines.size()).forEach(c-> bingo.addCard(lines.get(c)));

        final List<Integer> numbers = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::valueOf).boxed().toList();
        assertEquals(4512, bingo.play(numbers));
    }
}
