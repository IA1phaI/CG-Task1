package ru.vsu.cs.course2.a1pha;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawPanel extends JPanel {
    private int width, height;
    private double k;
    private Color[] starColors = new Color[]{
            new Color(162, 228, 255),
            new Color(100, 208, 189),
            new Color(220, 140, 120),
            new Color(186, 172, 194),
            new Color(255, 255, 255)
    };

    private SimplePlanet[] planets;
    private BigStar sun;
    private SmallStar[] stars;
    private ArrayList<Comet> cometsBack;
    private ArrayList<Comet> cometsFore;
    private ArrayList<FallingStar> fallingStars;
    private Point systemCenter = new Point();

    private int[] planetsX = new int[]{170, 205, 245, 285, 375, 530, 630, 710};
    private int[] planetsRadiuses = new int[]{10, 18, 20, 15, 70, 50, 30, 30};
    private int[] beltRadiuses = new int[]{0, 0, 0, 0, 90, 100, 40, 50};
    private Color[] normalPlanetsPalette = new Color[] {
            Color.yellow.darker(),
            Color.orange.darker(),
            Color.cyan.darker(),
            Color.red.darker(),
            new Color(180, 120, 60),
            Color.orange.darker().darker().darker(),
            Color.cyan.darker().darker().darker(),
            Color.blue.darker()
    };

    private Color normalSunMainColor = Color.orange;
    private Color noramalSunLightColor = Color.orange;

    private Color[] invasionPlanetsPalette = new Color[] {
            Color.yellow.darker().darker().darker().darker(),
            Color.orange.darker().darker().darker().darker(),
            Color.cyan.darker().darker().darker().darker(),
            Color.red.darker().darker().darker().darker(),
            new Color(180, 120, 60).darker().darker().darker(),
            Color.orange.darker().darker().darker().darker().darker().darker(),
            Color.cyan.darker().darker().darker().darker().darker().darker(),
            Color.blue.darker().darker().darker().darker()
    };

    private Color invasionSunMainColor = Color.black;
    private Color invasionSunLightColor = Color.red;

    private boolean isInvasion = false;

    private final Random random = new Random();

    public DrawPanel(int frameWidth, int frameHeight) {
        k = ((double) frameWidth) / 800;
        this.width = frameWidth - 36;
        this.height = frameHeight - 90;

        stars = generateStars(100);

        systemCenter = new Point((int) (- 300 * k), height / 2);

        cometsBack =  new ArrayList<>();
        changeCometsCount(cometsBack, 2);

        cometsFore = new ArrayList<>();
        changeCometsCount(cometsFore, 1);

        fallingStars = new ArrayList<>();
        changeFallingStarCount(fallingStars, 2);

        planets = new SimplePlanet[8];
        for (int i = 0; i < planets.length; i++) {
            if (beltRadiuses[i] == 0) {
                planets[i] = new SimplePlanet(
                        (int) (planetsX[i] * k),
                        (int) systemCenter.getY(),
                        (int) (planetsRadiuses[i] * k),
                        normalPlanetsPalette[i],
                        systemCenter);
            } else {
                planets[i] = new BeltedPlanet(
                        (int) (planetsX[i] * k),
                        (int) systemCenter.getY(),
                        (int) (planetsRadiuses[i] * k),
                        (int) (beltRadiuses[i] * k),
                        normalPlanetsPalette[i],
                        systemCenter);
            }
        }

        sun = new BigStar(
                (int) systemCenter.getX(),
                (int) systemCenter.getY(),
                (int) (400 * k),
                normalSunMainColor,
                noramalSunLightColor);
    }

    private SmallStar[] generateStars(int count) {
        SmallStar[] stars = new SmallStar[count];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new SmallStar(
                    random.nextInt(width),
                    random.nextInt(height),
                    random.nextInt((int) (1 * k), (int) (3 * k)),
                    starColors[random.nextInt(starColors.length)]);
        }

        return stars;
    }

    private void reuseComet(Comet comet) {
        comet.setX(random.nextInt(width * 3 / 2));
        comet.setY((int) (-10 * k));
        comet.setHeadRadius((int) (random.nextInt(1, 10) * k));
        comet.setColor(starColors[random.nextInt(3)]);
    }

    private void reuseFallingStar(FallingStar fallingStar) {
        fallingStar.setX(random.nextInt((int) (-100 * k), width));
        fallingStar.setY(random.nextInt((int) (-100 * k), height));
        fallingStar.setLength((int) (random.nextInt(5, 100) * k));
        fallingStar.setHeight((int) (random.nextInt(1, 4) * k));
        fallingStar.setColor(Color.white);
        fallingStar.setLeftTravelDistance(random.nextInt(50, 5000));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(new Color(11, 26, 59));
        for (SmallStar star : stars) {
            star.draw(g2d);
        }

        for (FallingStar fallingStar : fallingStars) {
            fallingStar.draw(g2d);
        }

        for (Comet comet : cometsBack) {
            comet.draw(g2d);
        }

        for (PaintableObject planet : planets) {
            planet.draw(g2d);
        }

        sun.draw(g2d);

        for (Comet comet : cometsFore) {
            comet.draw(g2d);
        }
    }

    public void toggleInvasion() {
        if (isInvasion) {
            isInvasion = false;
            changeFallingStarCount(fallingStars, 2);
            changeCometsCount(cometsBack, 2);
            changeCometsCount(cometsFore, 1);
            recolorCosmicBodies(
                    normalPlanetsPalette,
                    normalSunMainColor,
                    noramalSunLightColor);
        } else {
            isInvasion = true;
            changeFallingStarCount(fallingStars, 5000);
            changeCometsCount(cometsBack, 10);
            changeCometsCount(cometsFore, 5);
            recolorCosmicBodies(
                    invasionPlanetsPalette,
                    invasionSunMainColor,
                    invasionSunLightColor);
        }
        repaint();
    }

    public void motion() {
        for (int i = 0; i < cometsBack.size(); i++) {
            if (isCometOnScreen(cometsBack.get(i))) {
                reuseComet(cometsBack.get(i));
            }
            cometsBack.get(i).translate(-(i + 2), i + 2);
        }

        for (int i = 0; i < cometsFore.size(); i++) {
            if (isCometOnScreen(cometsFore.get(i))) {
                reuseComet(cometsFore.get(i));
            }
            cometsFore.get(i).translate(-(i + 2), i + 2);
        }

        for (int i = 0; i < fallingStars.size(); i++) {
            if (fallingStars.get(i).getLeftTravelDistance() < 0) {
                 reuseFallingStar(fallingStars.get(i));
            }

            fallingStars.get(i).translate(- ((i + 1) * 100), (i + 1) * 100);
        }
        repaint();
    }

    private void changeCometsCount(ArrayList<Comet> comets, int newCount) {
        while (comets.size() > newCount) {
            comets.remove(comets.size() - 1);
        }
        while (comets.size() < newCount) {
            comets.add(generateComet());
        }
        comets = new ArrayList<>();
        for (int i = 0; i < newCount; i++){
            comets.add(generateComet());
        }
    }

    private Comet generateComet() {
        Comet comet = new Comet();
        reuseComet(comet);

        return comet;
    }

    private void changeFallingStarCount(@NotNull ArrayList<FallingStar> fallingStars, int newCount) {
        while (fallingStars.size() > newCount) {
            fallingStars.remove(fallingStars.size() - 1);
        }
        while (fallingStars.size() < newCount) {
            fallingStars.add(generateFallingStar());
        }
    }

    private FallingStar generateFallingStar() {
        FallingStar fallingStar = new FallingStar();
        reuseFallingStar(fallingStar);
        return fallingStar;
    }


    private boolean isCometOnScreen(Comet comet) {
        return comet.getX() < - (comet.getTailLength() + comet.getHeadRadius() * 2) ||
                comet.getY() > height + comet.getTailLength() + comet.getHeadRadius() * 2;
    }

    private void recolorCosmicBodies(Color[] planetsPalette, Color bigStarMainColor, Color bigStarLightColor) {
        for (int i = 0; i < planets.length; i++) {
            planets[i].setColor(planetsPalette[i]);
        }

        sun.setColors(bigStarMainColor, bigStarLightColor);
    }
}
