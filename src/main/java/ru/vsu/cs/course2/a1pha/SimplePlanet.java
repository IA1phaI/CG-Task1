package ru.vsu.cs.course2.a1pha;

import java.awt.*;

public class SimplePlanet extends PaintableObject{
    private int radius;
    private Color color;
    private Point systemCenter;

    public SimplePlanet(int x, int y, int radius, Color color, Point systemCenter) {
        super(x, y);
        this.color = color;
        this.radius = radius;
        this.systemCenter = systemCenter;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    void draw(Graphics2D g2d) {
        if (systemCenter != null) {
            drawOrbit(g2d);
        }

        g2d.setColor(color);
        g2d.fillOval(getX() - radius, getY() - radius, radius << 1, radius << 1);
    }

    void drawOrbit(Graphics2D g2d) {
        g2d.setColor(Color.gray);
        int orbitRadius = (int) systemCenter.distance(getX(), getY());
        g2d.drawOval(
                (int) (systemCenter.getX() - orbitRadius),
                getY() - orbitRadius, orbitRadius << 1,
                orbitRadius << 1);
    }
}
