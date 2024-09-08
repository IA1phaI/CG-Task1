package ru.vsu.cs.course2.a1pha;

import java.awt.*;

public interface PaintableObjectInterface {
    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    void move(int x, int y);

    void translate(int dx, int dy);

    void draw(Graphics2D g2d);
}
