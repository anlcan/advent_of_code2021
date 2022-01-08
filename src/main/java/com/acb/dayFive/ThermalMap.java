package com.acb.dayFive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * Created on 31.12.21.
 */
public class ThermalMap {

    public record Point(int x, int y){

        public static Point of(final String str){
            return new Point(Integer.parseInt(str.split(",")[0]),Integer.parseInt(str.split(",")[1]));
        }


        public static List<Point> inBetweenStraight(Point a, Point b){

            if (a.x == b.x){
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

        public static List<Point> inBetweenDiagonal(Point a, Point b){
            if ( abs(a.x - b.x) == abs(a.y - b.y)) {
                return IntStream.rangeClosed(0, abs(a.x - b.x))
                        .boxed()
                        .map(i -> new Point(a.x + i, a.y + i))
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        }
    }

    public static final class Coordinate{
        final Point p;
        final int value;
        public Coordinate(Coordinate coordinate){
            this(new Point(coordinate.p.x, coordinate.p.y),coordinate.value+1);
        }
        public Coordinate (Point p){
            this(p,1);
        }

        public Coordinate(Point p, int i) {
            this.p = p;
            this.value   = i;
        }
    }

    final Map<Point, Coordinate> coordinates;

    private static List<Point> readCommand(final String str){
        final String[] split = str.split("->");
        assert split.length == 2;
        return List.of(Point.of(split[0].trim()), Point.of(split[1].trim()));
    }

    public static List<Point> parseCommand(final String str){
        final List<Point> points = readCommand(str);
        return Point.inBetweenStraight(points.get(0), points.get(1));
    }

    public static List<Point> parseCommandAll(final String str){
        final List<Point> points = readCommand(str);

        final List<Point> path = Point.inBetweenStraight(points.get(0), points.get(1));
        if (path.size() == 0) {
            final List<Point> path2 = Point.inBetweenDiagonal(points.get(0), points.get(1));
            if (path2.size() ==0 ){
                throw new RuntimeException(str);
            }
            return path2;
        } else {
            return path;
        }
    }

    public static List<List<Point>> readStraight(final List<String> commands){
        return commands.stream().map(ThermalMap::parseCommand)
                .collect(Collectors.toList());
    }

    public static List<List<Point>> readAll(final List<String> commands) {
        return commands.stream().map(ThermalMap::parseCommandAll)
                .collect(Collectors.toList());
    }

    public ThermalMap(List<List<Point>> pointsList){
        coordinates = new HashMap<>();
        pointsList.forEach(pointList-> pointList.forEach(p-> {
                Coordinate c = coordinates.get(p);
                if (c == null){
                    coordinates.put(p, new Coordinate(p));
                } else {
                    coordinates.put(p, new Coordinate(c));
                }
            })
        );
    }

    public long countOverLappedPoints(){
        return coordinates.values().stream().filter(c -> c.value >= 2).count();
    }

}
