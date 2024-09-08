package ru.vsu.cs.course2.a1pha.CosmicBodies;

import ru.vsu.cs.course2.a1pha.PaintableObject;

import java.awt.*;

public class SmallStar extends PaintableObject {
    private int radius;
    private Color color;

    public SmallStar(int x, int y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke((float) radius / 3));
        g2d.drawOval(getX() - radius, getY() -radius, radius << 1, radius << 1);
    }
}
