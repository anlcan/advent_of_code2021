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
        final List<ThermalMap.Point> points = ThermalMap.Point.inBetweenStraight(new ThermalMap.Point(1, 1), new ThermalMap.Point(1, 3));
        assertEquals(3, points.size());

        final List<ThermalMap.Point> diagonalPoints = ThermalMap.Point.inBetweenDiagonal(new ThermalMap.Point(1, 1), new ThermalMap.Point(3, 3));
        assertEquals(3, diagonalPoints.size());

        final List<ThermalMap.Point> diagonalPoints2 = ThermalMap.parseCommandAll("9,7 -> 7,9");
        assertEquals(3, diagonalPoints2.size());

        final List<ThermalMap.Point> diagonalPoints3 = ThermalMap.parseCommandAll("8,0 -> 0,8");
        assertEquals(9, diagonalPoints3.size());

        final List<ThermalMap.Point> commands = ThermalMap.parseCommand("1,1 -> 1,3");
        assertEquals(3, commands.size());

        assertEquals(new ThermalMap.Point(1,1), new ThermalMap.Point(1,1));
        assertNotEquals(new ThermalMap.Point(1,2), new ThermalMap.Point(1,1));

    }

    @Test
    public void intersectionTest() {
        final List<ThermalMap.Point> points1 = ThermalMap.Point.inBetweenStraight(new ThermalMap.Point(1, 1), new ThermalMap.Point(1, 3));
        final List<ThermalMap.Point> point2 = ThermalMap.parseCommand("0,2 -> 2,2");

        final List<List<ThermalMap.Point>> points11 = List.of(points1, point2);
        final ThermalMap thermalMap = new ThermalMap(points11);
        assertEquals(1, thermalMap.countOverLappedPoints());

    }
    @Test
    public void sampleTest(){
        final List<String> strings = Util.readStrings("/day5/sample.txt");
        final List<List<ThermalMap.Point>> commands = ThermalMap.readStraight(strings);
        ThermalMap tm = new ThermalMap(commands);
        assertEquals(5, tm.countOverLappedPoints());

        final List<List<ThermalMap.Point>> commandsAll = ThermalMap.readAll(strings);
        ThermalMap tm2 = new ThermalMap(commandsAll);
        assertEquals(12, tm2.countOverLappedPoints());
    }

    @Test
    public void inputOneTest(){
        final List<String> strings = Util.readStrings("/day5/input1.txt");
        final List<List<ThermalMap.Point>> commands = ThermalMap.readStraight(strings);
        ThermalMap tm = new ThermalMap(commands);
        assertEquals(7644, tm.countOverLappedPoints());
    }

    @Test
    public void inputTwoTest(){
        final List<String> strings = Util.readStrings("/day5/input1.txt");
        final List<List<ThermalMap.Point>> points = ThermalMap.readAll(strings);
        ThermalMap tm = new ThermalMap(points);
        assertEquals(7644, tm.countOverLappedPoints());
    }
}
