package ru.vsu.cs.course2.a1pha.paintable.objects;

import ru.vsu.cs.course2.a1pha.paintable.PaintableObject;
import ru.vsu.cs.course2.a1pha.utils.Point;

import java.awt.*;

public class Orbit extends PaintableObject {
    private final int width;
    private final int height;
    private final Color color;

    public Orbit(int centerX, int centerY, int width, int height, Color color) {
        super(centerX, centerY);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Orbit(int centerX, int centerY, int radius, Color color) {
        this(centerX, centerY, radius << 1, radius << 1, color);
    }

    public Orbit(Point orbitCenter, int radius, Color color) {
        this(orbitCenter.x(), orbitCenter.y(), radius, color);
    }

    public Orbit(Point orbitCenter, int width, int height, Color color) {
        this(orbitCenter.x(), orbitCenter.y(), width, height, color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(
                getX() - (width >> 1),
                getY() - (height >> 1),
                width,
                height);
    }
}
