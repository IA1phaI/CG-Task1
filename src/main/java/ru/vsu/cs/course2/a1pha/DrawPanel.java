package ru.vsu.cs.course2.a1pha;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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

    private List<SimplePlanet> planets;
    private BigStar sun;
    private SmallStar[] stars;
    Comet[] comets;

    private final Random random = new Random();

    public DrawPanel(int frameWidth, int frameHeight) {
        k = ((double) frameWidth) / 800;
        this.width = frameWidth - 36;
        this.height = frameHeight - 90;

        generateStars(100);

        Point systemCenter = new Point((int) (- 300 * k), height / 2);

        comets = new Comet[5] ;
        for (int i = 0; i < comets.length; i++){
                comets[i] = generateComet();
        }

        planets = new ArrayList<>();
        planets.add(new SimplePlanet(
                (int) (170 * k),
                (int) systemCenter.getY(),
                (int) (10 * k),
                Color.yellow.darker(),
                systemCenter));

        planets.add(new SimplePlanet(
                (int) (205 * k),
                (int) systemCenter.getY(),
                (int) (18 * k),
                Color.orange.darker(),
                systemCenter));

        planets.add(new SimplePlanet(
                (int) (245 * k),
                (int) systemCenter.getY(),
                (int) (20 * k),
                Color.cyan.darker(),
                systemCenter));

        planets.add(new SimplePlanet(
                (int) (285 * k),
                (int) systemCenter.getY(),
                (int) (15 * k),
                Color.red.darker(),
                systemCenter));

        planets.add(new BeltedPlanet(
                (int) (375 * k),
                (int) systemCenter.getY(),
                (int) (70 * k),
                (int) (90 * k),
                new Color(180, 120, 60),
                systemCenter));

        planets.add(new BeltedPlanet(
                (int) (530 * k),
                (int) systemCenter.getY(),
                (int) (50 * k),
                (int) (100 * k),
                Color.orange.darker().darker().darker(),
                systemCenter));

        planets.add(new BeltedPlanet(
                (int) (630 * k),
                (int) systemCenter.getY(),
                (int) (30 * k),
                (int) (40 * k),
                Color.cyan.darker().darker().darker(),
                systemCenter));

        planets.add(new BeltedPlanet(
                (int) (710 * k),
                (int) systemCenter.getY(),
                (int) (30 * k),
                (int) (50 * k),
                Color.blue.darker(),
                systemCenter));

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
                random.nextInt(width),
                (int) (-10 * k),
                (int) (random.nextInt(1, 10) * k),
                starColors[random.nextInt(3)]);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(new Color(11, 26, 59));
        for (SmallStar star : stars) {
            star.draw(g2d);
        }

        for (Comet comet : comets) {
            comet.draw(g2d);
        }

        for (PaintableObject planet : planets) {
            planet.draw(g2d);
        }

        sun.draw(g2d);
    }

    public void toggleInvasion() {
        sun.setColors(Color.black, Color.red);
        repaint();
    }

    public void motion() {
        for (int i = 0; i < comets.length; i++) {
            if (comets[i].getX() < - (comets[i].getTailLength() + comets[i].getHeadRadius() * 2) ||
                    comets[i].getY() > height + comets[i].getTailLength() + comets[i].getHeadRadius() * 2) {
                comets[i].move(random.nextInt(width), 0);
                comets[i] = generateComet();
            }
            System.out.printf("%d %d\n", comets[i].getX(), comets[i].getY());
            comets[i].translate(-(i + 2), i + 2);
        }
        repaint();
    }
}
