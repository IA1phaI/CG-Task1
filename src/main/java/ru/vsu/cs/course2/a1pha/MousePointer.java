package ru.vsu.cs.course2.a1pha;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MousePointer extends PaintableObject {
    private int positionsBufferSize;
    Queue<Point> positionsBuffer;

    public MousePointer(int positionsBufferSize) {
        this.positionsBufferSize = positionsBufferSize;
        positionsBuffer = new LinkedList<>();
        move(-100, 100);
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        assert positionsBuffer != null;
        if (positionsBuffer.size() == positionsBufferSize) {
            positionsBuffer.poll();
        }

        positionsBuffer.add(new Point(x, y));
    }

    @Override
    public void draw(Graphics2D g2d) {
        Point pastPosition = positionsBuffer.peek();
        assert pastPosition != null;
        double angle = Math.atan((getX() - pastPosition.x()) * 1.0 / (getY() - pastPosition.y()));
        g2d.rotate(-angle, getX(), getY());
        g2d.setColor(Color.pink);
        g2d.fillPolygon(new int[]{getX(), getX() - 12, getX() + 12}, new int[]{getY(), getY() - 50, getY() - 50}, 3);
        g2d.rotate(-angle, getX(), getY());
    }
}
