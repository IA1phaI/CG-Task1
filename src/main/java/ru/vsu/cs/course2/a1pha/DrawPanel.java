package ru.vsu.cs.course2.a1pha;

import javax.swing.*;
import java.awt.*;
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
    private Comet[] cometsBack;
    private Comet[] cometsFore;
    private FallingStar[] fallingStars;

    private int[] planetsX = new int[]{170, 205, 245, 285, 375, 530, 630, 710};
    private int[] planetsRadiuses = new int[]{10, 18, 20, 15, 70, 50, 30, 30};
    private int[] beltRadiuses = new int[]{0, 0, 0, 0, 90, 100, 40, 50};
    private Color[] normalPlanetColors = new Color[] {
            Color.yellow.darker(),
            Color.orange.darker(),
            Color.cyan.darker(),
            Color.red.darker(),
            new Color(180, 120, 60),
            Color.orange.darker().darker().darker(),
            Color.cyan.darker().darker().darker(),
            Color.blue.darker()
    };

    private final Random random = new Random();

    public DrawPanel(int frameWidth, int frameHeight) {
        k = ((double) frameWidth) / 800;
        this.width = frameWidth - 36;
        this.height = frameHeight - 90;

        generateStars(100);

        Point systemCenter = new Point((int) (- 300 * k), height / 2);

        cometsBack = new Comet[2] ;
        for (int i = 0; i < cometsBack.length; i++){
            cometsBack[i] = generateComet();
        }

        cometsFore = new Comet[1];
        for (int i = 0; i < cometsFore.length; i++){
            cometsFore[i] = generateComet();
        }

        fallingStars = new FallingStar[2];
        for (int i = 0; i < fallingStars.length; i++) {
            fallingStars[i] = generateFallingStar();
        }

        planets = new SimplePlanet[8];
        for (int i = 0; i < planets.length; i++) {
            if (beltRadiuses[i] == 0) {
                planets[i] = new SimplePlanet(
                        (int) (planetsX[i] * k),
                        (int) systemCenter.getY(),
                        (int) (planetsRadiuses[i] * k),
                        normalPlanetColors[i],
                        systemCenter);
            } else {
                planets[i] = new BeltedPlanet(
                        (int) (planetsX[i] * k),
                        (int) systemCenter.getY(),
                        (int) (planetsRadiuses[i] * k),
                        (int) (beltRadiuses[i] * k),
                        normalPlanetColors[i],
                        systemCenter);
            }
        }

        sun = new BigStar(
                (int) systemCenter.getX(),
                (int) systemCenter.getY(),
                (int) (400 * k),
                Color.orange,
                Color.orange);
    }

    private void generateStars(int starsCount) {
        stars = new SmallStar[starsCount];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new SmallStar(
                    random.nextInt(width),
                    random.nextInt(height),
                    random.nextInt((int) (1 * k), (int) (3 * k)),
                    starColors[random.nextInt(starColors.length)]);
        }
    }

    private Comet generateComet() {
        return new Comet(
                random.nextInt(width * 3 / 2),
                (int) (-10 * k),
                (int) (random.nextInt(1, 10) * k),
                starColors[random.nextInt(3)]);
    }

    private FallingStar generateFallingStar() {
        return new FallingStar(
                random.nextInt((int) (-100 * k), width),
                random.nextInt(height),
                (int) (random.nextInt(5, 100) * k),
                (int) (random.nextInt(1, 4) * k),
                Color.white,
                random.nextInt(50, 5000));
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
        sun.setColors(Color.black, Color.red);
        repaint();
    }

    public void motion() {
        for (int i = 0; i < cometsBack.length; i++) {
            if (isCometOnScreen(cometsBack[i])) {
                cometsBack[i] = generateComet();
            }
            cometsBack[i].translate(-(i + 2), i + 2);
        }

        for (int i = 0; i < cometsFore.length; i++) {
            if (isCometOnScreen(cometsFore[i])) {
                cometsFore[i] = generateComet();
            }
            cometsFore[i].translate(-(i + 2), i + 2);
        }

        for (int i = 0; i < fallingStars.length; i++) {
            if (fallingStars[i].getLeftTravelDistance() < 0) {
                fallingStars[i] = generateFallingStar();
            }

            fallingStars[i].translate(- ((i + 1) * 50), (i + 1) * 50);
        }
        repaint();
    }

    private boolean isCometOnScreen(Comet comet) {
        return comet.getX() < - (comet.getTailLength() + comet.getHeadRadius() * 2) ||
                comet.getY() > height + comet.getTailLength() + comet.getHeadRadius() * 2;
    }
}
