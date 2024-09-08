package ru.vsu.cs.course2.a1pha;

import java.awt.*;

/**
 * All int sizes are indicated for 800x600
 */
public class PlanetSystemSettings {

    public final Color spaceColor = new Color(11, 26, 59);

    public final int[] planetsX = new int[]{170, 205, 245, 285, 375, 530, 630, 710};
    public final int[] planetsRadiuses = new int[]{10, 18, 20, 15, 70, 50, 30, 30};
    public final int[] beltRadiuses = new int[]{0, 0, 0, 0, 90, 100, 40, 50};
    public final Color[] normalPlanetsPalette = new Color[] {
            Color.yellow.darker(),
            Color.orange.darker(),
            Color.cyan.darker(),
            Color.red.darker(),
            new Color(180, 120, 60),
            Color.orange.darker().darker().darker(),
            Color.cyan.darker().darker().darker(),
            Color.blue.darker()
    };

    public final Color[] starColors = new Color[]{
            new Color(162, 228, 255),
            new Color(100, 208, 189),
            new Color(220, 140, 120),
            new Color(186, 172, 194),
            new Color(255, 255, 255)
    };

    public final Color normalSunMainColor = Color.orange;
    public final Color noramalSunLightColor = Color.orange;

    public static final Color[] invasionPlanetsPalette = new Color[] {
            Color.yellow.darker().darker().darker().darker(),
            Color.orange.darker().darker().darker().darker(),
            Color.cyan.darker().darker().darker().darker(),
            Color.red.darker().darker().darker().darker(),
            new Color(180, 120, 60).darker().darker().darker(),
            Color.orange.darker().darker().darker().darker().darker().darker(),
            Color.cyan.darker().darker().darker().darker().darker().darker(),
            Color.blue.darker().darker().darker().darker()
    };

    public final Color invasionSunMainColor = Color.black;
    public final Color invasionSunLightColor = Color.red;

    public final int fallingStarMinLength = 10;
    public final int fallingStarMaxLength = 100;
    public final int fallingStarMinHeight = 1;
    public final int fallingStarMaxHeight = 4;
    public final int fallingStarMinTravelDistance = 5;
    public final int fallingStarMaxTravelDistance = 10;

    public final int cometMinHeadRadius = 1;
    public final int cometMaxHeadRadius = 10;

    public final int smallStarMinRadius = 1;
    public final int smallStarMaxRadius = 3;

    public final int sunRadius = 400;
}
