package ru.vsu.cs.course2.a1pha.utils;

public class MathUtils {

    public static double getVectorAngle(int x, int y) {
        return Math.acos(x / getHypotenuse(x, y)) * (y < 0 ? -1 : 1);
    }

    public static double getHypotenuse(int k1, int k2) {
        return Math.sqrt(k1 * k1 + k2 * k2);
    }
}
