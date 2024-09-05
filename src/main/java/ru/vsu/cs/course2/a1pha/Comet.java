package ru.vsu.cs.course2.a1pha;

import java.awt.*;

public class Comet extends PaintableObject{
    private int headRadius;
    private int tailLength;
    private Color color;

    public Comet(int x, int y, int headRadius, Color color) {
        super(x, y);
        this.headRadius = headRadius;
        this.tailLength = headRadius * 30;
        this.color = color;
    }

    @Override
    void draw(Graphics2D g2d) {

        int tailStartX = (int)(getX() - headRadius * Math.sqrt(2) / 2);
        int tailStartY = (int)(getY() + headRadius * Math.sqrt(2) / 2);
        g2d.setColor(color);
        g2d.setPaint(new RadialGradientPaint(
                getX(),
                getY(),
                (float) tailLength * 2,
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
                        tailStartX,
                        (int)(tailStartX + tailLength * Math.cos(-0.698132)),
                        (int)(tailStartX + tailLength * Math.cos(-0.872665))
                },
                new int[]{
                        tailStartY,
                        (int)(tailStartY + tailLength * Math.sin(-0.698132)),
                        (int)(tailStartY + tailLength * Math.sin(-0.872665))},
                3
        );


        g2d.setPaint(new RadialGradientPaint(
                getX(),
                getY(),
                (float) tailLength * 2,
                new float[]{.05f, .95f},
                new Color[]{
                        Color.white,
                        new Color(0,0,0,0)}
        ));
        g2d.fillPolygon(
                new int[]{
                        tailStartX,
                        (int)(tailStartX + tailLength / 1.5 * Math.cos(-0.750492)),
                        (int)(tailStartX + tailLength / 1.5 * Math.cos(-0.820305))
                },
                new int[]{
                        tailStartY,
                        (int)(tailStartY + tailLength / 1.5 * Math.sin(-0.750492)),
                        (int)(tailStartY + tailLength / 1.5 * Math.sin(-0.820305))
                },
                3
        );

        g2d.setColor(color);
        g2d.fillOval(getX() - headRadius, getY() - headRadius, headRadius << 1, headRadius << 1);
    }
}
