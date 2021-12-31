package com.acb.dayFive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created on 31.12.21.
 */
public class ThermalMap {

    public record Point(int x, int y){

        public static Point of(final String str){
            return new Point(Integer.parseInt(str.split(",")[0]),Integer.parseInt(str.split(",")[1]));
        }
        public static List<Point> inBetween(Point a, Point b){
            //            only consider horizontal and vertical lines:
            if (a.x != b.x && a.y != b.y) {
                return new ArrayList<>();
            }
            if (a.x == b.x){
                return IntStream.rangeClosed(min(a.y, b.y), max(a.y, b.y))
                        .boxed()
                        .map(i -> new Point(a.x, i))
                        .collect(Collectors.toList());
            } else {
                return IntStream.rangeClosed(min(a.x, b.x), max(a.x, b.x))
                        .boxed()
                        .map(i -> new Point(i, a.y))
                        .collect(Collectors.toList());
            }
        }
    }
    public static final class Coordinate{
        final Point p;
        final int value;
        public Coordinate(Coordinate coordinate){
            this.p = new Point(coordinate.p.x, coordinate.p.y);
            this.value = coordinate.value+1;
        }
        public Coordinate (Point p){
            this.p = new Point(p.x, p.y);
            this.value = 1;
        }

        public Coordinate(Point p, int i) {
            this.p = p;
            this.value   = i;
        }
    }

    final Map<Point, Coordinate> coordinates;

    public static List<Point> parseCommand(final String str){
        final String[] split = str.split("->");
        assert split.length == 2;
        return Point.inBetween(Point.of(split[0].trim()), Point.of(split[1].trim()));
    }


    public static List<List<Point>> read(final List<String> commands){
        return commands.stream().map(ThermalMap::parseCommand)
                        .filter(p-> p.size()>0)
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
