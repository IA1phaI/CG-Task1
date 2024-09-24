package ru.vsu.cs.course2.a1pha.paintable;

import ru.vsu.cs.course2.a1pha.utils.Point;

import java.awt.*;

public abstract class PaintableObject {
    private int x;
    private int y;

    public PaintableObject(Point point) {
        this(point.x(), point.y());
    }

    public PaintableObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int x, int y) {
        setX(x);
        setY(y);
    }

    public void translate(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public abstract void draw(Graphics2D g2d);
}
