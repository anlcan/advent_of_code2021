package com.acb.aux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * Created on 08.01.22.
 */
public record Point(int x, int y) {

    public static Point of(final String str) {
        return new Point(Integer.parseInt(str.split(",")[0]), Integer.parseInt(str.split(",")[1]));
    }


    public static List<Point> inBetweenStraight(Point a, Point b) {

        if (a.x == b.x) {
            return IntStream.rangeClosed(min(a.y, b.y), max(a.y, b.y))
                    .boxed()
                    .map(i -> new Point(a.x, i))
                    .collect(Collectors.toList());
        } else if (a.y == b.y) {
            return IntStream.rangeClosed(min(a.x, b.x), max(a.x, b.x))
                    .boxed()
                    .map(i -> new Point(i, a.y))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public static List<Point> inBetweenDiagonal(Point a, Point b) {
        if (abs(a.x - b.x) == abs(a.y - b.y)) {
            return IntStream.rangeClosed(0, abs(a.x - b.x))
                    .boxed()
                    .map(i -> new Point(a.x + i, a.y + i))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
    //four adjacent locations (up, down, left, and right)
    public List<Point> adjacent(){
        return List.of(
                new Point(x, y+1),
                new Point(x, y-1),
                new Point(x+1, y),
                new Point(x-1, y));
    }

    public List<Point> adjacentFull(){
        final ArrayList<Point> points = new ArrayList<>(adjacent());
        points.addAll(List.of(
                new Point(x+1, y + 1),
                new Point(x-1, y - 1),
                new Point(x + 1, y-1),
                new Point(x - 1, y+1))
        );
        return points;
    }
}
