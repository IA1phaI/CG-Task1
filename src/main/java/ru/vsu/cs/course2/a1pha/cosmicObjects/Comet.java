package ru.vsu.cs.course2.a1pha.cosmicObjects;

import ru.vsu.cs.course2.a1pha.TickMovingObject;

import java.awt.*;

public class Comet extends TickMovingObject {
    private int headRadius;
    private int tailLength;


    private Color color;

    public Comet(int x, int y, int headRadius, int speed, double angle, Color color) {
        super(x, y, speed, angle);
        this.headRadius = headRadius;
        this.tailLength = headRadius * 40;
        this.color = color;
    }

    public int getHeadRadius() {
        return headRadius;
    }

    public void setHeadRadius(int headRadius) {
        this.headRadius = headRadius;
        this.tailLength = headRadius * 40;
    }

    public int getTailLength() {
        return tailLength;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(getMovingAngle(), getX(), getY());
        g2d.setColor(color);
        g2d.setPaint(new RadialGradientPaint(
                getX(),
                getY(),
                (float) (tailLength * 0.9),
                new float[]{.05f, .95f},
                new Color[]{
                        color,
                        new Color(
                                color.getRed(),
                                color.getGreen(),
                                color.getBlue(),
                                0
                        )}
        ));
        g2d.fillPolygon(
                new int[]{
                        getX() + headRadius,
                        getX() - headRadius - tailLength,
                        getX() - headRadius - tailLength,
                },
                new int[]{
                        getY(),
                        getY() + headRadius * 10,
                        getY() - headRadius * 10},
                3
        );

        g2d.setPaint(new RadialGradientPaint(
                getX(),
                getY(),
                (float) (tailLength * 0.7),
                new float[]{.05f, .95f},
                new Color[]{
                        Color.white,
                        new Color(0,0,0,0)}
        ));
        g2d.fillPolygon(
                new int[]{
                        getX() + headRadius,
                        getX() - headRadius - tailLength,
                        getX() - headRadius - tailLength,
                },
                new int[]{
                        getY(),
                        getY() + headRadius * 5,
                        getY() - headRadius * 5},
                3
        );
        g2d.rotate(-getMovingAngle(), getX(), getY());
        g2d.setColor(color);
        g2d.fillOval(getX() - headRadius, getY() - headRadius, headRadius << 1, headRadius << 1);
    }
}
