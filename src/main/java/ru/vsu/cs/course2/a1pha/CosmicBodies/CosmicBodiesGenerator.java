package ru.vsu.cs.course2.a1pha.CosmicBodies;

import ru.vsu.cs.course2.a1pha.PlanetSystemSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CosmicBodiesGenerator {
    private int height, width;
    private double scalingFactor;
    private final Random random = new Random();
    PlanetSystemSettings settings;

    public CosmicBodiesGenerator(int height, int width, double scalingFactor) {
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
                    random.nextInt((int) (settings.smallStarMinRadius * scalingFactor), (int) (settings.smallStarMaxRadius * scalingFactor)),
                    settings.starColors[random.nextInt(settings.starColors.length)]));
        }

        return stars;
    }

    public void reuseComet(Comet comet) {
        comet.setX(random.nextInt(width * 3 / 2));
        comet.setY((int) (-settings.cometMaxHeadRadius * scalingFactor));
        comet.setHeadRadius((int) (random.nextInt(settings.cometMinHeadRadius, settings.cometMaxHeadRadius) * scalingFactor));
        comet.setSpeed((int) (random.nextInt(settings.cometMinSpeed, settings.cometMaxSpeed) * scalingFactor));
        comet.setMovingAngle(Math.toRadians(settings.fallingAngle));
        comet.setColor(settings.starColors[random.nextInt(3)]);
    }

    public Comet generateComet() {
        Comet comet = new Comet();
        reuseComet(comet);

        return comet;
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
        fallingStar.setLength((int) (random.nextInt(settings.fallingStarMinLength,settings.fallingStarMaxLength) * scalingFactor));
        fallingStar.setHeight((int) (random.nextInt(settings.fallingStarMinHeight,settings.fallingStarMaxHeight) * scalingFactor));
        fallingStar.setColor(Color.white);
        fallingStar.setMovingAngle(Math.toRadians(settings.fallingAngle));
        fallingStar.setSpeed((int) (random.nextInt(settings.fallingStarMinSpeed, settings.fallingStarMaxSpeed) * scalingFactor));
        fallingStar.setLeftTravelDistance((int) (random.nextInt(settings.fallingStarMinTravelDistance, settings.fallingStarMaxTravelDistance) * scalingFactor));
    }

    public FallingStar generateFallingStar() {
        FallingStar fallingStar = new FallingStar();
        reuseFallingStar(fallingStar);

        return fallingStar;
    }

    public ArrayList<FallingStar> generateFallingStars(int newCount) {
        ArrayList<FallingStar> fallingStars = new ArrayList<>(newCount);
        while (fallingStars.size() < newCount) {
            fallingStars.add(generateFallingStar());
        }

        return fallingStars;
    }
}
