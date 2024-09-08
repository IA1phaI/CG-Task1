package ru.vsu.cs.course2.a1pha;

public abstract class TickMovingObject extends PaintableObject {
    int speed = 0;
    int angdeg = 0;

    public TickMovingObject() {}

    public TickMovingObject(int x, int y, int speed, int angdeg) {
        super(x, y);
        this.speed = speed;
        this.angdeg = angdeg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMovingAngleDeg() {
        return angdeg;
    }

    public void setMovingAngleDeg(int angdeg) {
        this.angdeg = angdeg;
    }

    public void tickMove() {
        setX((int) (getX() - speed * Math.cos(Math.toRadians(angdeg))));
        setY((int) (getY() + speed * Math.sin(Math.toRadians(angdeg))));
    }
}
