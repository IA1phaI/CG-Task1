package ru.vsu.cs.course2.a1pha.cosmic_objects;

import ru.vsu.cs.course2.a1pha.TickMovingObject;

import java.awt.*;

public class FallingStar extends TickMovingObject {
    private int length;
    private int width;
    private int leftTravelDistance;
    private Color color;

    public FallingStar(int x, int y, int length, int width, int xSpeed, int ySpeed, int distanceToTravel, Color color) {
        super(x, y, xSpeed, ySpeed);
        this.length = length;
        this.width = width;
        this.leftTravelDistance = distanceToTravel;
        this.color = color;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
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
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.rotate(getMovingAngle(), getX(), getY());
        g2d.fillRoundRect(getX() - (length / 2), getY() - (width / 2), length, width, 5, 5);
        g2d.rotate(-getMovingAngle(), getX(), getY());
    }
    
    @Override
    public void translate(int deltaX, int deltaY) {
        super.translate(deltaX, deltaY);
        leftTravelDistance -= (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public void tickMove() {
        super.tickMove();
        leftTravelDistance -= (int) getSpeed();
    }
}
