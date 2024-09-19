package ru.vsu.cs.course2.a1pha;

import ru.vsu.cs.course2.a1pha.cosmicObjects.Comet;
import ru.vsu.cs.course2.a1pha.cosmicObjects.FallingStar;
import ru.vsu.cs.course2.a1pha.cosmicObjects.stars.SmallStar;

import java.util.ArrayList;
import java.util.Random;

public class PaintableObjectGenerator {
    private final int height, width;
    private final double scalingFactor;
    private final Random random = new Random();
    PlanetSystemSettings settings;

    public PaintableObjectGenerator(int height, int width, double scalingFactor) {
        this.height = height;
        this.width = width;
        this.scalingFactor = scalingFactor;
        settings = new PlanetSystemSettings();
    }

    public ArrayList<SmallStar> generateSmallStars(int count) {
        ArrayList<SmallStar> stars = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            stars.add(new SmallStar(
                    random.nextInt(width),
                    random.nextInt(height),

                    random.nextInt(
                            (int) (settings.smallStarMinRadius * scalingFactor),
                            (int) (settings.smallStarMaxRadius * scalingFactor)),

                    settings.starColors[random.nextInt(settings.starColors.length)]));
        }

        return stars;
    }

    public void reuseComet(Comet comet) {
        comet.setX(random.nextInt(width * 3 / 2));
        comet.setY((int) (-settings.cometMaxHeadRadius * scalingFactor));

        comet.setHeadRadius((int) (random.nextInt(
                settings.cometMinHeadRadius,
                settings.cometMaxHeadRadius) * scalingFactor));

        comet.setSpeed((int) (random.nextInt(
                settings.cometMinSpeed,
                settings.cometMaxSpeed) * scalingFactor));
        comet.setMovingAngle(Math.toRadians(settings.fallingAngle));

        comet.setColor(settings.starColors[random.nextInt(3)]);
    }

    public Comet generateComet() {
        return new Comet(
                random.nextInt(width * 3 / 2),
                (int) (-settings.cometMaxHeadRadius * scalingFactor),

                (int) (random.nextInt(
                        settings.cometMinHeadRadius,
                        settings.cometMaxHeadRadius) * scalingFactor),

                (int) (random.nextInt(settings.cometMinSpeed,
                        settings.cometMaxSpeed) * scalingFactor),

                Math.toRadians(settings.fallingAngle),

                settings.starColors[random.nextInt(3)]
        );
    }

    public ArrayList<Comet> generateComets(int newCount) {
        ArrayList<Comet> comets = new ArrayList<>(newCount);

        while (comets.size() < newCount) {
            comets.add(generateComet());
        }

        return comets;
    }

    public void reuseFallingStar(FallingStar fallingStar) {
        fallingStar.setX(random.nextInt(0, (int) (width + 300 * scalingFactor)));
        fallingStar.setY(random.nextInt((int) (-100 * scalingFactor), height));

        fallingStar.setLength((int) (random.nextInt(
                settings.fallingStarMinLength,
                settings.fallingStarMaxLength) * scalingFactor));

        fallingStar.setHeight((int) (random.nextInt(
                settings.fallingStarMinHeight,
                settings.fallingStarMaxHeight) * scalingFactor));

        fallingStar.setSpeed((int) (random.nextInt(
                settings.fallingStarMinSpeed,
                settings.fallingStarMaxSpeed) * scalingFactor));

        fallingStar.setLeftTravelDistance((int) (random.nextInt(
                settings.fallingStarMinTravelDistance,
                settings.fallingStarMaxTravelDistance) * scalingFactor));

        fallingStar.setMovingAngle(Math.toRadians(settings.fallingAngle));

        fallingStar.setColor(settings.fallingStarColor);
    }

    public FallingStar generateFallingStar() {
        return new FallingStar(
                random.nextInt(0, (int) (width + 300 * scalingFactor)),
                random.nextInt((int) (-100 * scalingFactor), height),

                (int) (random.nextInt(
                        settings.fallingStarMinLength,
                        settings.fallingStarMaxLength) * scalingFactor),

                (int) (random.nextInt(
                        settings.fallingStarMinHeight,
                        settings.fallingStarMaxHeight) * scalingFactor),

                (int) (random.nextInt(
                        settings.fallingStarMinSpeed,
                        settings.fallingStarMaxSpeed) * scalingFactor),

                (int) (random.nextInt(
                        settings.fallingStarMinTravelDistance,
                        settings.fallingStarMaxTravelDistance) * scalingFactor),

                Math.toRadians(settings.fallingAngle),

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

    public void redirectUFO(UFO ufo) {
        ufo.setSpeed(random.nextInt(2, 14));
        ufo.setMovingAngle(ufo.getMovingAngle() + Math.toRadians(random.nextInt(165, 195)));
    }

    public UFO generateUFO() {
        return new UFO(
                random.nextInt(0, width),
                random.nextInt(0, height),
                random.nextInt(settings.ufoMinWidth, settings.ufoMaxWidth),
                random.nextInt(settings.ufoMinSpeed, settings.ufoMaxSpeed),
                Math.toRadians(random.nextInt(0, 360)),
                settings.ufoColors[random.nextInt(0, settings.ufoColors.length)]
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
