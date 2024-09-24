package ru.vsu.cs.course2.a1pha.cosmic_objects.stars;

import ru.vsu.cs.course2.a1pha.PaintableObject;

import java.awt.*;

public class BigStar extends PaintableObject {
    private final int radius;

    private Color mainColor;
    private Color lightColor;

    public BigStar(int x, int y, int radius, Color mainColor, Color lightColor) {
        super(x, y);
        this.radius = radius;
        this.mainColor = mainColor;
        this.lightColor = lightColor;
    }

    public int getRadius() {
        return radius;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public Color getLightColor() {
        return lightColor;
    }

    public void setLightColor(Color lightColor) {
        this.lightColor = lightColor;
    }

    public void setColors(Color mainColor, Color lightColor) {
        setMainColor(mainColor);
        setLightColor(lightColor);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(new RadialGradientPaint(
                getX(),
                getY(),
                radius * 2,
                new float[]{.05f, .95f},
                new Color[]{
                        lightColor,
                        new Color(
                                lightColor.getRed(),
                                lightColor.getGreen(),
                                lightColor.getBlue(),
                                0
                        )}
        ));
        g2d.fillOval(
                getX() - radius * 2,
                getY() - radius * 2,
                radius * 4,
                radius * 4
        );
        g2d.setColor(mainColor);
        g2d.fillOval(
                getX() - radius,
                getY() - radius,
                radius << 1,
                radius << 1);
    }

}
