package com.acb.dayNine;

import com.acb.aux.Coordinate;
import com.acb.aux.Point;
import com.acb.aux.Util;

import java.util.*;

/**
 * Created on 08.01.22.
 */
public class Lava {
    private HashMap<Point, Coordinate> map = new HashMap<>();

    public Lava(List<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            map.put(coordinate.getPoint(), coordinate);
        }
    }

    public static Lava of(List<String> input) {
        final List<List<Integer>> integers = input.stream()
                .map(Util::readIntegers)
                .toList();

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            for (int i1 = 0; i1 < integers.get(0).size(); i1++) {
                coordinates.add(new Coordinate(new Point(i, i1), integers.get(i).get(i1)));
            }
        }

        return  new Lava(coordinates);
    }

    public boolean isLowPoint(final Coordinate c){
        final List<Point> adjacents = c.getPoint().adjacent();

        return adjacents.stream()
                .map(p -> map.get(p))
                .filter(Objects::nonNull)
                .mapToInt(Coordinate::getValue)
                .allMatch(value -> c.getValue() < value);
    }

    public List<Coordinate> lowPoints(){
        return map.values().stream().filter(this::isLowPoint).toList();
    }

    public int big3size(){
        return allBasins().stream().limit(3).reduce(1, (a, b) -> a * b);
    }

    public List<Integer> allBasins(){
        final List<Coordinate> coordinates = lowPoints();
        return coordinates.stream().map(this::basin).mapToInt(List::size).boxed().sorted(Comparator.reverseOrder()).toList();
    }


    // A basin is all locations that eventually flow downward to a single low point.
    public List<Coordinate> basin(final Coordinate lpc){
        Set<Coordinate> result = new HashSet<>(allDownwards(lpc));
        while (true){
            final List<Coordinate> newPoints = result.stream()
                    .map(this::allDownwards)
                    .flatMap(Collection::stream)
                    .distinct()
                    .toList();
            if (!result.addAll(newPoints))
                break;
        };
        result.add(lpc);
        return result.stream().toList();
    }

    public List<Coordinate> allDownwards(final Coordinate target){
        final List<Coordinate> adjacents = target.getPoint().adjacent()
                .stream().map(p -> map.get(p))
                .filter(Objects::nonNull)
                .filter(c -> c.getValue() < 9 && c.getValue() > target.getValue())
                .toList();
        return adjacents;

    }

    public int riskLevel(){
        return lowPoints().stream().mapToInt(c -> c.getValue()+1).sum();
    }
}
