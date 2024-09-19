package ru.vsu.cs.course2.a1pha;

import ru.vsu.cs.course2.a1pha.cosmicObjects.Comet;
import ru.vsu.cs.course2.a1pha.cosmicObjects.FallingStar;
import ru.vsu.cs.course2.a1pha.cosmicObjects.stars.SmallStar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PaintableObjectGenerator {
    private final int frameHeight, frameWidth;
    private final double scalingFactor;
    private final Random random = new Random();
    PlanetSystemSettings settings;

    public PaintableObjectGenerator(int frameHeight, int frameWidth, double scalingFactor) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.scalingFactor = scalingFactor;
        settings = new PlanetSystemSettings();
    }

    public ArrayList<SmallStar> generateSmallStars(int count) {
        ArrayList<SmallStar> stars = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            stars.add(new SmallStar(
                    random.nextInt(frameWidth),
                    random.nextInt(frameHeight),

                    random.nextInt(
                            (int) (settings.smallStarMinRadius * scalingFactor),
                            (int) (settings.smallStarMaxRadius * scalingFactor)),

                    settings.starColors[random.nextInt(settings.starColors.length)]));
        }

        return stars;
    }


    // COMETS

    private int generateCometHeadRadius() {
        return (int) (random.nextInt(
                settings.cometMinHeadRadius,
                settings.cometMaxHeadRadius) * scalingFactor);
    }

    private int generateCometAbsAxisSpeed() {
        return (int) (random.nextInt(
                settings.cometMinAbsAxisSpeed,
                settings.cometMaxAbsAxisSpeed) * scalingFactor);
    }

    private Color generateCometColor() {
        return settings.starColors[random.nextInt(3)];
    }

    public void reuseComet(Comet comet) {
        comet.setX(random.nextInt(frameWidth * 3 / 2));
        comet.setY((int) (-settings.cometMaxHeadRadius * scalingFactor));

        comet.setHeadRadius(generateCometHeadRadius());

        int absAxisSpeed = generateCometAbsAxisSpeed();
        comet.setXSpeed(-absAxisSpeed);
        comet.setYSpeed(absAxisSpeed);

        comet.setColor(generateCometColor());
    }

    public Comet generateComet() {
        int absAxisSpeed = generateCometAbsAxisSpeed();
        return new Comet(
                random.nextInt(frameWidth * 3 / 2),
                (int) (-settings.cometMaxHeadRadius * scalingFactor),
                generateCometHeadRadius(),
                -absAxisSpeed,
                absAxisSpeed,
                generateCometColor()
        );
    }

    public ArrayList<Comet> generateComets(int newCount) {
        ArrayList<Comet> comets = new ArrayList<>(newCount);

        while (comets.size() < newCount) {
            comets.add(generateComet());
        }

        return comets;
    }

    // FALLING STARS

    public int generateFallingStarLength() {
        return (int) (random.nextInt(
                settings.fallingStarMinLength,
                settings.fallingStarMaxLength) * scalingFactor);
    }

    public int generateFallingStarWidth() {
        return (int) (random.nextInt(
                settings.fallingStarMinHeight,
                settings.fallingStarMaxWidth) * scalingFactor);
    }

    public int generateFallingStarAbsAxisSpeed() {
        return (int) (random.nextInt(
                settings.fallingStarMinAbsAxisSpeed,
                settings.fallingStarMaxAbsAxisSpeed) * scalingFactor);
    }

    public int generateFallingStarTravelDistance() {
        return (int) (random.nextInt(
                settings.fallingStarMinTravelDistance,
                settings.fallingStarMaxTravelDistance) * scalingFactor);
    }

    public void reuseFallingStar(FallingStar fallingStar) {
        fallingStar.setX(random.nextInt(0, (int) (frameWidth + 300 * scalingFactor)));
        fallingStar.setY(random.nextInt((int) (-100 * scalingFactor), frameHeight));

        fallingStar.setLength(generateFallingStarLength());

        fallingStar.setWidth(generateFallingStarWidth());

        int absAxisSpeed = generateFallingStarAbsAxisSpeed();
        fallingStar.setXSpeed(-absAxisSpeed);
        fallingStar.setYSpeed(absAxisSpeed);

        fallingStar.setLeftTravelDistance(generateFallingStarTravelDistance());

        fallingStar.setColor(settings.fallingStarColor);
    }

    public FallingStar generateFallingStar() {
        int absAxisSpeed = generateFallingStarAbsAxisSpeed();
        return new FallingStar(
                random.nextInt(0, (int) (frameWidth + 300 * scalingFactor)),
                random.nextInt((int) (-100 * scalingFactor), frameHeight),

                generateFallingStarLength(),
                generateFallingStarWidth(),
                -absAxisSpeed,
                absAxisSpeed,

                generateFallingStarTravelDistance(),
                settings.fallingStarColor
        );
    }

    public ArrayList<FallingStar> generateFallingStars(int newCount) {
        ArrayList<FallingStar> fallingStars = new ArrayList<>(newCount);
        while (fallingStars.size() < newCount) {
            fallingStars.add(generateFallingStar());
        }

        return fallingStars;
    }

    // UFO

    public int generateUFOWidth() {
        return (int) (random.nextInt(
                settings.ufoMinWidth,
                settings.ufoMaxWidth) * scalingFactor);
    }

    public int generateUFOAbsAxisSpeed() {
        return (int) (random.nextInt(settings.ufoMaxAbsAxisSpeed) * scalingFactor);
    }

    public int generateUFOAxisSpeed() {
        return generateUFOAbsAxisSpeed() * (random.nextInt(2) == 0 ? 1 : -1);
    }

    public Color generateUFOCorpsColor() {
        return settings.ufoColors[random.nextInt(0, settings.ufoColors.length)];
    }

    public void redirectUFO(UFO ufo) {
        int absAxisSpeed = 0;
        int axisSpeed = 0;
        while (absAxisSpeed == 0 && axisSpeed == 0) {
            absAxisSpeed = generateUFOAbsAxisSpeed();
            axisSpeed = generateUFOAxisSpeed(); 
        }
        if (ufo.getX() - (ufo.getWidth() >> 1) < 0) {
            ufo.setXSpeed(generateUFOAbsAxisSpeed());
            ufo.setYSpeed(generateUFOAxisSpeed());
        }
        if (ufo.getX() + (ufo.getWidth() >> 1) > frameWidth) {
            ufo.setXSpeed(-generateUFOAbsAxisSpeed());
            ufo.setYSpeed(generateUFOAxisSpeed());
        }
        if (ufo.getY() - (ufo.getWidth() >> 1) < 0) {
            ufo.setXSpeed(generateUFOAxisSpeed());
            ufo.setYSpeed(generateUFOAbsAxisSpeed());
        }
        if (ufo.getY() + (ufo.getWidth() >> 1) > frameHeight) {
            ufo.setXSpeed(generateUFOAxisSpeed());
            ufo.setYSpeed(-1 * generateUFOAbsAxisSpeed());
        }
    }

    public UFO generateUFO() {
        int axisSpeed1 = 0, axisSpeed2 = 0;
        while (axisSpeed1 == 0 && axisSpeed2 == 0) {
            axisSpeed1 = generateUFOAxisSpeed();
            axisSpeed2 = generateUFOAxisSpeed();
        }

        return new UFO(
                random.nextInt(0, frameWidth),
                random.nextInt(0, frameHeight),
                generateUFOWidth(),
                axisSpeed1,
                axisSpeed2,
                generateUFOCorpsColor()
        );
    }
    public ArrayList<UFO> generateUFOs(int newCount) {
        ArrayList<UFO> ufos = new ArrayList<>(newCount);
        while (ufos.size() < newCount) {
            ufos.add(generateUFO());
        }

        return ufos;
    }

}
