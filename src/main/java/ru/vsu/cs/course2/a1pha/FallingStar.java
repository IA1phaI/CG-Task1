package ru.vsu.cs.course2.a1pha;

import java.awt.*;

public class FallingStar extends PaintableObject {
    private int length;
    private int height;
    private Color color;
    private int leftTravelDistance;

    public FallingStar(){}

    public FallingStar(int x, int y, int length, int height, Color color, int distanceToTravel) {
        super(x, y);
        this.length = length;
        this.height = height;
        this.color = color;
        this.leftTravelDistance = distanceToTravel;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLeftTravelDistance() {
        return leftTravelDistance;
    }

    public void setLeftTravelDistance(int leftTravelDistance) {
        this.leftTravelDistance = leftTravelDistance;
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.rotate(-0.785398, getX(), getY());
        g2d.fillRoundRect(getX() - (length / 2), getY() - (height / 2), length, height, 5, 5);
        g2d.rotate(0.785398, getX(), getY());
    }
    
    @Override
    public void translate(int dx, int dy) {
        super.translate(dx, dy);
        leftTravelDistance -= (int) Math.sqrt(dx * dx + dy * dy);
    }
}
