package com.acb.aux;

/**
 * Created on 08.01.22.
 */
public final class Coordinate {
    final Point p;
    final int value;

    public Coordinate(Coordinate coordinate) {
        this(new Point(coordinate.p.x(), coordinate.p.y()), coordinate.value + 1);
    }

    public Coordinate(Point p) {
        this(p, 1);
    }

    public Coordinate(Point p, int i) {
        this.p = p;
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public Point getPoint() {
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Coordinate)obj).getPoint().equals(p);
    }
}
