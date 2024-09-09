package ru.vsu.cs.course2.a1pha;

import java.awt.*;

public class Projectile extends TickMovingObject {
    private int width;
    private int length;

    public Projectile(int x, int y, int speed, int length, int width, double angle) {
        super(x, y, speed, angle);
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.green.brighter());
        g2d.rotate(getMovingAngle(), getX(), getY());
        g2d.fillRoundRect(getX(), getY() - (width >> 1), length, width, width >> 1, width >> 1);
        g2d.rotate(-getMovingAngle(), getX(), getY());
    }
}
