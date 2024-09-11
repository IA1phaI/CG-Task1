package ru.vsu.cs.course2.a1pha.CosmicBodies;

import ru.vsu.cs.course2.a1pha.TickMovingObject;

import java.awt.*;

public class FallingStar extends TickMovingObject {
    private int length;
    private int height;
    private int leftTravelDistance;
    private Color color;

    public FallingStar(int x, int y, int length, int height, int speed, int distanceToTravel, double angle, Color color) {
        super(x, y, speed, angle);
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
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.rotate(getMovingAngle(), getX(), getY());
        g2d.fillRoundRect(getX() - (length / 2), getY() - (height / 2), length, height, 5, 5);
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
        leftTravelDistance -= getSpeed();
    }
}
