package ru.vsu.cs.course2.a1pha;

import ru.vsu.cs.course2.a1pha.utils.MathUtils;

public abstract class TickMovingObject extends PaintableObject {
    private int xSpeed;
    private int ySpeed;

    public TickMovingObject(int x, int y, int xSpeed, int ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public TickMovingObject(int x, int y, int speed, double movingAngle) {
        this(x, y, (int) (speed * Math.cos(movingAngle)), (int) (speed * Math.sin(movingAngle)));
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getSpeed() {
        return MathUtils.getHypotenuse(xSpeed, ySpeed);
    }

    public double getMovingAngle() {
       return MathUtils.getVectorAngle(xSpeed, ySpeed);
    }

    public void setMovingAngle(double angle) {
        double speed = getSpeed();
        xSpeed = (int) (speed * Math.cos(angle));
        ySpeed = (int) (speed * Math.sin(angle));
    }

    public void tickMove() {
        setX((getX() + xSpeed));
        setY((getY() + ySpeed));
    }
}
