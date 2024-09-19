package ru.vsu.cs.course2.a1pha;

import java.awt.*;

public class UFO extends TickMovingObject {
    private final int width;
    private final Color corpsColor;

    private static final Color RAY_COLOR = Color.cyan;
    private static final Color CABIN_COLOR = Color.yellow;
    private static final Color HEAD_COLOR = CABIN_COLOR.darker().darker();

    public UFO(int x, int y, int width, int speed, double movingAngle, Color corpsColor) {
        super(x, y, speed, movingAngle);
        this.width = width;
        this.corpsColor = corpsColor;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int height = width >> 2;
        int cabinRadius = width >> 2;
        int headRadius = cabinRadius >> 2;
        int rayLength = width;

        g2d.setPaint(new RadialGradientPaint(
                getX(),
                getY() - cabinRadius,
                (float) (rayLength * 0.9),
                new float[]{.05f, .95f},
                new Color[]{RAY_COLOR, new Color(0, 0, 0, 0)}
        ));
        g2d.fillPolygon(
                new int[]{
                        getX(),
                        getX() + cabinRadius,
                        getX() - cabinRadius,
                },
                new int[]{
                        getY() - cabinRadius,
                        getY() + width,
                        getY() + width},
                3
        );

        g2d.setColor(CABIN_COLOR);
        g2d.fillArc(
                getX() - cabinRadius,
                getY() - cabinRadius,
                cabinRadius << 1,
                cabinRadius << 1,
                0,
                180);

        g2d.setColor(HEAD_COLOR);
        g2d.fillOval(
                getX() - headRadius,
                getY() - (headRadius >> 1) * 3 - (height >> 1),
                headRadius << 1,
                headRadius << 1);

        g2d.setColor(corpsColor);
        g2d.fillOval(getX() - (width >> 1),
                getY() - (height >> 1) ,
                width,
                height);
    }
}
