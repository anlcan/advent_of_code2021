package com.acb.advent2021;

import com.acb.Util;
import com.acb.dayFive.ThermalMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created on 31.12.21.
 */
public class DayFive {

    @Test
    public void inBetweenTest(){
        final List<ThermalMap.Point> points = ThermalMap.Point.inBetween(new ThermalMap.Point(1, 1), new ThermalMap.Point(1, 3));
        assertEquals(3, points.size());

        final List<ThermalMap.Point> commands = ThermalMap.parseCommand("1,1 -> 1,3");
        assertEquals(3, points.size());

        assertEquals(new ThermalMap.Point(1,1), new ThermalMap.Point(1,1));
        assertNotEquals(new ThermalMap.Point(1,2), new ThermalMap.Point(1,1));

    }

    @Test
    public void intersectionTest() {
        final List<ThermalMap.Point> points1 = ThermalMap.Point.inBetween(new ThermalMap.Point(1, 1), new ThermalMap.Point(1, 3));
        final List<ThermalMap.Point> point2 = ThermalMap.parseCommand("0,2 -> 2,2");

        final List<List<ThermalMap.Point>> points11 = List.of(points1, point2);
        System.out.println(points11);
        final ThermalMap thermalMap = new ThermalMap(points11);
        assertEquals(1, thermalMap.countOverLappedPoints());

    }
    @Test
    public void sampleTest(){
        final List<String> strings = Util.readStrings("/day5/sample.txt");
        final List<List<ThermalMap.Point>> commands = ThermalMap.read(strings);
        ThermalMap tm = new ThermalMap(commands);
        assertEquals(5, tm.countOverLappedPoints());
    }

    @Test
    public void inputOneTest(){
        final List<String> strings = Util.readStrings("/day5/input1.txt");
        final List<List<ThermalMap.Point>> commands = ThermalMap.read(strings);
        ThermalMap tm = new ThermalMap(commands);
        assertEquals(5, tm.countOverLappedPoints());
    }
}
