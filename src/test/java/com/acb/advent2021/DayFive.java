package com.acb.advent2021;

import com.acb.Util;
import com.acb.aux.Point;
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
        final List<Point> points = Point.inBetweenStraight(new Point(1, 1), new Point(1, 3));
        assertEquals(3, points.size());

        final List<Point> diagonalPoints = Point.inBetweenDiagonal(new Point(1, 1), new Point(3, 3));
        assertEquals(3, diagonalPoints.size());

        final List<Point> diagonalPoints2 = ThermalMap.parseCommandAll("9,7 -> 7,9");
        assertEquals(3, diagonalPoints2.size());

        final List<Point> diagonalPoints3 = ThermalMap.parseCommandAll("8,0 -> 0,8");
        assertEquals(9, diagonalPoints3.size());

        final List<Point> commands = ThermalMap.parseCommand("1,1 -> 1,3");
        assertEquals(3, commands.size());

        assertEquals(new Point(1,1), new Point(1,1));
        assertNotEquals(new Point(1,2), new Point(1,1));

    }

    @Test
    public void intersectionTest() {
        final List<Point> points1 = Point.inBetweenStraight(new Point(1, 1), new Point(1, 3));
        final List<Point> point2 = ThermalMap.parseCommand("0,2 -> 2,2");

        final List<List<Point>> points11 = List.of(points1, point2);
        final ThermalMap thermalMap = new ThermalMap(points11);
        assertEquals(1, thermalMap.countOverLappedPoints());

    }
    @Test
    public void sampleTest(){
        final List<String> strings = Util.readStrings("/day5/sample.txt");
        final List<List<Point>> commands = ThermalMap.readStraight(strings);
        ThermalMap tm = new ThermalMap(commands);
        assertEquals(5, tm.countOverLappedPoints());

        final List<List<Point>> commandsAll = ThermalMap.readAll(strings);
        ThermalMap tm2 = new ThermalMap(commandsAll);
        assertEquals(12, tm2.countOverLappedPoints());
    }

    @Test
    public void inputOneTest(){
        final List<String> strings = Util.readStrings("/day5/input1.txt");
        final List<List<Point>> commands = ThermalMap.readStraight(strings);
        ThermalMap tm = new ThermalMap(commands);
        assertEquals(7644, tm.countOverLappedPoints());
    }

    @Test
    public void inputTwoTest(){
        final List<String> strings = Util.readStrings("/day5/input1.txt");
        final List<List<Point>> points = ThermalMap.readAll(strings);
        ThermalMap tm = new ThermalMap(points);
        assertEquals(7644, tm.countOverLappedPoints());
    }
}
