package ru.unn.agile.CalculateSquare.Model;

/**
 * Created by Redvix on 02.12.2014.
 */
public class SphereSector {
    private final double radiusSphere, radiusSegment;
    private static final int COEFF_SQUARE = 2;

    public SphereSector(final double radiusSphere, final double radiusSegment) {
        this.radiusSphere = radiusSphere;
        this.radiusSegment = radiusSegment;
    }

    public double calculateSquare() {
        return COEFF_SQUARE * Math.PI * radiusSphere * (radiusSphere + radiusSegment / 2
                - Math.sqrt(Math.pow(radiusSphere, 2) + Math.pow(radiusSegment, 2)));
    }
}

