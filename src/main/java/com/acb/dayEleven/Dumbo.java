package com.acb.dayEleven;

import com.acb.aux.Coordinate;
import com.acb.aux.Point;
import com.acb.aux.Util;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created on 08.01.22.
 */
public class Dumbo {

    private Coordinate coordinate;
    private int flashCount = 0;


    public Dumbo(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean step(){
        this.coordinate = new Coordinate(coordinate);
        return coordinate.getValue() == 10;
    }

    public void step_done(){
        if (coordinate.getValue()>9){
            coordinate = new Coordinate(coordinate.getPoint(), 0);
            flashCount++;
        }
    }

    public int getFlashCount() {
        return flashCount;
    }

    public static class Octopi {
        HashMap<Point,  Dumbo> fleat = new HashMap<>();
        int width;
        int height;

        public static Octopi of(final List<String> lines){
            final List<List<Integer>> integers = lines.stream()
                    .map(Util::readIntegers)
                    .toList();

            List<Coordinate> coordinates = new ArrayList<>();
            final int width = integers.size();
            for (int x = 0; x < width
                     ; x++) {
                for (int y = 0; y < integers.get(0).size(); y++) {
                    coordinates.add(new Coordinate(new Point(x, y), integers.get(x).get(y)));
                }
            }
            return new Octopi(coordinates);
        }

        public Octopi(List<Coordinate> coordinates) {
            for (Coordinate coordinate : coordinates) {
                fleat.put(coordinate.getPoint(), new Dumbo(coordinate));
            }
            width = coordinates.stream().mapToInt(c -> c.getPoint().x()).max(). getAsInt();
            height = coordinates.stream().mapToInt(c -> c.getPoint().y()).max().getAsInt();
        }

        public int stepOf(int count){
            IntStream.range(0, count).forEach(i -> this.step());
            print();
            return flashCount();
        }

        public void step(){
            print();
            final Collection<Dumbo> values = fleat.values();
            values.forEach(this::dumboStep);
            values.forEach(Dumbo::step_done);

        }
        public boolean dumboStep(final Dumbo d) {
            if (d.step()) {
                final List<Point> points = d.getCoordinate().getPoint().adjacentFull();
               // points.add(d.getCoordinate().getPoint());
                points.stream().map(p-> fleat.get(p))
                        .filter(Objects::nonNull)
                        .forEach(this::dumboStep);
                return true;
            } else {
                return false;
            }
        }

        public int flashCount(){
            return fleat.values().stream().mapToInt(Dumbo::getFlashCount).sum();
        }

        public void print(){
            for (int i = 0; i <= width; i++) {
                System.out.print("\n");
                for (int j = 0; j <= height; j++) {
                    System.out.print(fleat.get(new Point(i, j)).getCoordinate().getValue());
                }
            }
            System.out.println("\n-------------\n");
        }
    }
}
