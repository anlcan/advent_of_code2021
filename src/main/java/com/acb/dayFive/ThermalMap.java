package com.acb.dayFive;

import com.acb.aux.Coordinate;
import com.acb.aux.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 31.12.21.
 */
public class ThermalMap {

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
        return coordinates.values().stream().filter(c -> c.getValue() >= 2).count();
    }

}
