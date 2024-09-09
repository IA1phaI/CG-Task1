package ru.vsu.cs.course2.a1pha;

import org.jetbrains.annotations.NotNull;
import ru.vsu.cs.course2.a1pha.CosmicBodies.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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

    private final ArrayList<Projectile> projectiles;

    private Point systemCenterPoint;

    private boolean isInvasion = false;

    private final Random random = new Random();

    private MousePointer mousePointer;

    private CosmicBodiesGenerator generator;

    public final Font invasionTextFont;

    private int warningTimeCounter = 0;

    private boolean fire = false;

    public DrawPanel(int frameWidth, int frameHeight) {
        scalingFactor = ((double) frameWidth) / 800;
        this.width = frameWidth - 36;
        this.height = frameHeight - 66;

        mousePointer = new MousePointer(scalingFactor, 15);

        generator = new CosmicBodiesGenerator(height, width, scalingFactor);

        invasionTextFont = new Font(
                settings.invasionTextFontName,
                settings.invasionTextFontStyle,
                (int) (settings.invasionTextFontSize * scalingFactor));

        projectiles = new ArrayList<>();

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
                        systemCenterPoint.y(),
                        (int) (settings.planetsRadiuses[i] * scalingFactor),
                        settings.normalPlanetsPalette[i],
                        systemCenterPoint));
            } else {
                planets.add(new BeltedPlanet(
                        (int) (settings.planetsX[i] * scalingFactor),
                        systemCenterPoint.y(),
                        (int) (settings.planetsRadiuses[i] * scalingFactor),
                        (int) (settings.beltRadiuses[i] * scalingFactor),
                        settings.normalPlanetsPalette[i],
                        systemCenterPoint));
            }
        }

        sun = new BigStar(
                systemCenterPoint.x(),
                systemCenterPoint.y(),
                (int) (settings.sunRadius * scalingFactor),
                settings.normalSunMainColor,
                settings.noramalSunLightColor);

        this.setCursor(this.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new java.awt.Point(),
                null ) );
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                mousePointer.move(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mousePointer.move(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                projectiles.add(mousePointer.createProjectile());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fire = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                fire = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 32) {
                    toggleInvasion();
                }

                if (e.getKeyCode() == 17) {
                    fire = e.getID() == KeyEvent.KEY_PRESSED;
                }
                return false;
            }
        });
    }

    private <T extends PaintableObject> void drawObjectArray(@NotNull AbstractList<T> objects, Graphics2D g2d) {
        for (PaintableObject object : objects) {
            object.draw(g2d);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (fire) {
            projectiles.add(mousePointer.createProjectile());
        }
        Graphics2D g2d = (Graphics2D) g;

        drawObjectArray(stars, g2d);
        drawObjectArray(fallingStars, g2d);
        drawObjectArray(cometsBack, g2d);

        if (isInvasion) {
            drawObjectArray(invasionFallingStars, g2d);
            drawObjectArray(invasionCometsBack, g2d);
        }

        drawObjectArray(planets, g2d);

        sun.draw(g2d);

        drawObjectArray(cometsFore, g2d);

        if (isInvasion) {
            drawObjectArray(invasionCometsFore, g2d);
        }

        mousePointer.draw(g2d);
        drawObjectArray(projectiles, g2d);

        if (isInvasion) {
            drawWarning(g2d);
        }
    }

    private void drawWarning(Graphics2D g2d) {
        if (warningTimeCounter < settings.invasionTextBlinkTime) {
            g2d.setColor(settings.invasionTextColor);
            g2d.setFont(invasionTextFont);
            g2d.drawString(
                    settings.invasionText,
                    width / 11 * 2,
                    height / 5 * 3);
        } else if (warningTimeCounter > settings.invasionTextBlinkTime << 1) {
            warningTimeCounter = 0;
        }

        warningTimeCounter++;
    }

    private void animateComets(ArrayList<Comet> comets) {
        for (int i = 0; i < comets.size(); i++) {
            if (isCometOnScreen(comets.get(i))) {
                generator.reuseComet(comets.get(i));
            }
            comets.get(i).tickMove();
        }
    }

    private void animateFallingStars(ArrayList<FallingStar> fallingStars) {
        for (int i = 0; i < fallingStars.size(); i++) {
            if (fallingStars.get(i).getLeftTravelDistance() < 0 & random.nextInt(50) == 1) {
                generator.reuseFallingStar(fallingStars.get(i));
            }

            fallingStars.get(i).tickMove();
        }
    }

    private void animateProjectiles() {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).getX() < 0 ||
                    projectiles.get(i).getX() > width ||
                    projectiles.get(i).getY() < 0 ||
                    projectiles.get(i).getY() > height) {
                projectiles.remove(i);
            } else {
                projectiles.get(i).tickMove();
            }
        }
    }

    public void motion() {
        animateComets(cometsBack);
        animateComets(cometsFore);
        animateComets(invasionCometsBack);
        animateComets(invasionCometsFore);
        animateFallingStars(fallingStars);
        animateFallingStars(invasionFallingStars);
        animateProjectiles();

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
