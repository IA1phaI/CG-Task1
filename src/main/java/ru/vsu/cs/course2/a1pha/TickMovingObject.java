package ru.vsu.cs.course2.a1pha;

public abstract class TickMovingObject extends PaintableObject {
    int speed = 0;
    double angle = 0;

    public  TickMovingObject() {}

    public TickMovingObject(int x, int y, int speed, double angle) {
        super(x, y);
        this.speed = speed;
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMovingAngle() {
        return angle;
    }

    public void setMovingAngle(double angle) {
        this.angle = angle;
    }

    public void tickMove() {
        setX((int) (getX() + speed * Math.cos(angle)));
        setY((int) (getY() + speed * Math.sin(angle)));
    }
}
