package ru.vsu.cs.course2.a1pha.CosmicBodies;

import ru.vsu.cs.course2.a1pha.PaintableObject;

import java.awt.*;

public class SimplePlanet extends PaintableObject {
    private int radius;
    private Color color;
    private Point systemCenterPoint;

    public SimplePlanet(int x, int y, int radius, Color color, Point systemCenterPoint) {
        super(x, y);
        this.color = color;
        this.radius = radius;
        this.systemCenterPoint = systemCenterPoint;
    }

    public int getRadius() {
        return radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (systemCenterPoint != null) {
            drawOrbit(g2d);
        }

        g2d.setColor(color);
        g2d.fillOval(getX() - radius, getY() - radius, radius << 1, radius << 1);
    }

    void drawOrbit(Graphics2D g2d) {
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(2));
        int orbitRadius = (int) systemCenterPoint.distance(getX(), getY());
        g2d.drawOval(
                (int) (systemCenterPoint.getX() - orbitRadius),
                getY() - orbitRadius, orbitRadius << 1,
                orbitRadius << 1);
    }
}
