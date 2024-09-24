package ru.vsu.cs.course2.a1pha.utils;

public record Point(int x, int y) {
    public double distanceTo(int x, int y) {
        return distanceBetween(this.x, this.y, x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d; %d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point point) {
            return this.x == point.x && this.y == point.y;
        }
        return false;
    }

    public static double distanceBetween(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
