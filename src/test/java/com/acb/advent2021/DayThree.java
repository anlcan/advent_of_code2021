package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayThree.Diagnostic;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 18.12.21.
 */
public class DayThree {

    @Test
    void testBinary(){
        String input = "010101";
        int decimal=Integer.parseInt(input,2);
        assertEquals(decimal, Diagnostic.Binary.of(input).value());

    }

    @Test
    void sampleTest() {

        final List<String> binaries = Util.readStrings("/day3/sample.txt");
        assertEquals(198,
                new Diagnostic(binaries).readPC());
    }

    @Test
    void inputONeTest() {

        final List<String> binaries = Util.readStrings("/day3/input1.txt");
        assertEquals(2261546,
                new Diagnostic(binaries).readPC());
    }

}
