package ru.vsu.cs.course2.a1pha.paintable.planets;

import ru.vsu.cs.course2.a1pha.paintable.PaintableObject;

import java.awt.*;

public class SimplePlanet extends PaintableObject {
    private final int radius;
    private Color color;

    public SimplePlanet(int x, int y, int radius, Color color) {
        super(x, y);
        this.color = color;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(getX() - radius, getY() - radius, radius << 1, radius << 1);
    }


}
