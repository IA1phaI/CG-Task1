package ru.vsu.cs.course2.a1pha;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.AbstractList;

public class DrawUtils {

    public static <T extends PaintableObject> void drawObjectArray(@NotNull AbstractList<T> objects, Graphics2D g2d) {
        for (PaintableObject object : objects) {
            object.draw(g2d);
        }
    }}
