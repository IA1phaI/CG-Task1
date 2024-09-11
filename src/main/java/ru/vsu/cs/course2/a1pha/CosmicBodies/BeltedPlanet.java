package ru.vsu.cs.course2.a1pha.CosmicBodies;

import java.awt.*;
import ru.vsu.cs.course2.a1pha.Point;

public class BeltedPlanet extends SimplePlanet {
    private final int beltWidth;
    public BeltedPlanet(int x, int y, int radius, int beltRadius, Color color, Point systemCenter) {
        super(x, y, radius, color, systemCenter);
        this.beltWidth = beltRadius;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        g2d.setColor(new Color(255, 255, 255, 150));
        int beltHeight = getRadius() / 4;
        Graphics2D g2dBuffer;
        g2d.rotate(-0.5, getX(), getY());
        g2d.fillOval(getX() - beltWidth, getY() - beltHeight, beltWidth << 1, beltHeight << 1);
        g2d.rotate(0.5, getX(), getY());
    }
}
