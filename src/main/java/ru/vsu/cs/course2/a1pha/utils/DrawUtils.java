package ru.vsu.cs.course2.a1pha.utils;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.course2.a1pha.paintable.PaintableObject;

import java.awt.*;
import java.util.Collection;

public class DrawUtils {

    public static <T extends PaintableObject> void drawObjectArray(@NotNull Collection<T> objects, Graphics2D g2d) {
        for (PaintableObject object : objects) {
            object.draw(g2d);
        }
    }}
