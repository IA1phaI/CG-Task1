package ru.vsu.cs.course2.a1pha;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.course2.a1pha.CosmicBodies.*;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Random;

public class DrawPanel extends JPanel {
    private int width, height;
    private double scalingFactor;

    private final PlanetSystemSettings settings = new PlanetSystemSettings();

    private final ArrayList<SmallStar> stars;
    private final ArrayList<SimplePlanet> planets;
    private final BigStar sun;

    private final ArrayList<Comet> cometsBack;
    private final ArrayList<Comet> invasionCometsBack;
    private final ArrayList<Comet> cometsFore;
    private final ArrayList<Comet> invasionCometsFore;
    private final ArrayList<FallingStar> fallingStars;
    private final ArrayList<FallingStar> invasionFallingStars;
    private static Point systemCenterPoint;

    private boolean isInvasion = false;

    private final Random random = new Random();

    private CosmicBodiesGenerator generator;

    public DrawPanel(int frameWidth, int frameHeight) {
        scalingFactor = ((double) frameWidth) / 800;
        this.width = frameWidth - 36;
        this.height = frameHeight - 90;

        generator = new CosmicBodiesGenerator(height, width, scalingFactor);

        stars = generator.generateSmallStars(100);

        setBackground(settings.spaceColor);
        systemCenterPoint = new Point((int) (-300 * scalingFactor), height / 2);

        cometsBack = generator.generateComets(2);
        invasionCometsBack = generator.generateComets(20);

        cometsFore = generator.generateComets(1);
        invasionCometsFore = generator.generateComets(10);

        fallingStars = generator.generateFallingStars(2);
        invasionFallingStars = generator.generateFallingStars(200);

        planets = new ArrayList<>(settings.planetsX.length);
        for (int i = 0; i < settings.planetsX.length; i++) {
            if (settings.beltRadiuses[i] == 0) {
                planets.add(new SimplePlanet(
                        (int) (settings.planetsX[i] * scalingFactor),
                        (int) systemCenterPoint.getY(),
                        (int) (settings.planetsRadiuses[i] * scalingFactor),
                        settings.normalPlanetsPalette[i],
                        systemCenterPoint));
            } else {
                planets.add(new BeltedPlanet(
                        (int) (settings.planetsX[i] * scalingFactor),
                        (int) systemCenterPoint.getY(),
                        (int) (settings.planetsRadiuses[i] * scalingFactor),
                        (int) (settings.beltRadiuses[i] * scalingFactor),
                        settings.normalPlanetsPalette[i],
                        systemCenterPoint));
            }
        }

        sun = new BigStar(
                (int) systemCenterPoint.getX(),
                (int) systemCenterPoint.getY(),
                (int) (settings.sunRadius * scalingFactor),
                settings.normalSunMainColor,
                settings.noramalSunLightColor);
    }

    private <T extends PaintableObject> void drawObjectArrays(@NotNull AbstractList<T> objects, Graphics2D g2d) {
        for (PaintableObject object : objects) {
            object.draw(g2d);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        drawObjectArrays(stars, g2d);
        drawObjectArrays(fallingStars, g2d);
        drawObjectArrays(cometsBack, g2d);

        if (isInvasion) {
            drawObjectArrays(invasionFallingStars, g2d);
            drawObjectArrays(invasionCometsBack, g2d);
        }

        drawObjectArrays(planets, g2d);

        sun.draw(g2d);

        drawObjectArrays(cometsFore, g2d);

        if (isInvasion) {
            drawObjectArrays(invasionCometsFore, g2d);
        }
    }

    private void animateComets(ArrayList<Comet> comets) {
        for (int i = 0; i < comets.size(); i++) {
            if (isCometOnScreen(comets.get(i))) {
                generator.reuseComet(comets.get(i));
            }
            comets.get(i).translate(-(i + 2), i + 2);
        }
    }

    private void animateFallingStars(ArrayList<FallingStar> fallingStars) {
        for (int i = 0; i < fallingStars.size(); i++) {
            if (fallingStars.get(i).getLeftTravelDistance() < 0 & random.nextInt(5) == 1) {
                generator.reuseFallingStar(fallingStars.get(i));
            }

            fallingStars.get(i).translate(
                    - ((i + 1) * 100),
                    (i + 1) * 100);
        }
    }

    public void motion() {
        animateComets(cometsBack);
        animateComets(cometsFore);
        animateComets(invasionCometsBack);
        animateComets(invasionCometsFore);
        animateFallingStars(fallingStars);
        animateFallingStars(invasionFallingStars);

        repaint();
    }

    private boolean isCometOnScreen(Comet comet) {
        return comet.getX() < - (comet.getTailLength() + comet.getHeadRadius() * 2) ||
                comet.getY() > height + comet.getTailLength() + comet.getHeadRadius() * 2;
    }

    public void toggleInvasion() {
        if (isInvasion) {
            isInvasion = false;
            recolorCosmicBodies(
                    settings.normalPlanetsPalette,
                    settings.normalSunMainColor,
                    settings.noramalSunLightColor);
        } else {
            isInvasion = true;
            recolorCosmicBodies(
                    PlanetSystemSettings.invasionPlanetsPalette,
                    settings.invasionSunMainColor,
                    settings.invasionSunLightColor);
        }
        repaint();
    }

    private void recolorCosmicBodies(Color[] planetsPalette, Color bigStarMainColor, Color bigStarLightColor) {
        for (int i = 0; i < planets.size(); i++) {
            planets.get(i).setColor(planetsPalette[i]);
        }

        sun.setColors(bigStarMainColor, bigStarLightColor);
    }
}
