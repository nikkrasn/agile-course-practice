package ru.unn.agile.Triangle;

public class Triangle {
    private final Point pointA;
    private final Point pointB;
    private final Point pointC;

    public Triangle(final double aX, final double aY,
                    final double bX, final double bY,
                    final double cX, final double cY) {
        Point pointA = new Point(aX, aY);
        Point pointB = new Point(bX, bY);
        Point pointC = new Point(cX, cY);
        if (!isTriangle(pointA, pointB, pointC)) {
            throw new IllegalArgumentException("Triangle is formed by 3 points not on line ");
        }
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

    }

    public static boolean isTriangle(final Point pointA, final Point pointB, final Point pointC) {
        boolean answer = pointA.equals(pointB) || pointB.equals(pointC) || pointC.equals(pointA);
        if (!answer) {
            answer = answer || pointC.isOnStraightLine(pointA, pointB);
        }
        return !answer;
    }

    public double[] countLengths() {
        final int pointNum = 3;
        double[] lengths = new double[pointNum];
        lengths[0] = pointA.getDistance(pointB);
        lengths[1] = pointB.getDistance(pointC);
        lengths[2] = pointC.getDistance(pointA);

        return lengths;
    }

    public double countPerimeter() {
        double perimeter = pointA.getDistance(pointB);
        perimeter += pointB.getDistance(pointC);
        perimeter += pointC.getDistance(pointA);

        return perimeter;
    }

    public double countSpace() {
        final double half = 0.5;
        double halfPerimeter = half * countPerimeter();
        double[] lengths = countLengths();

        double space = halfPerimeter;
        space *= halfPerimeter - lengths[0];
        space *= halfPerimeter - lengths[1];
        space *= halfPerimeter - lengths[2];
        space = Math.sqrt(space);

        return space;
    }

    private double countTriangleCosineFromSides(final double a, final double b, final double c) {
        final double half = 0.5;
        double result = half * (a * a + b * b - c * c) / (a * b);
        return result;
    }

    public double[] countAnglesCosine() {
        final int angleNum = 3;
        double[] anglesCosine = new double[angleNum];
        double[] lengths = countLengths();

        anglesCosine[0] = countTriangleCosineFromSides(lengths[0], lengths[2], lengths[1]);
        anglesCosine[1] = countTriangleCosineFromSides(lengths[0], lengths[1], lengths[2]);
        anglesCosine[2] = countTriangleCosineFromSides(lengths[1], lengths[2], lengths[0]);

        return anglesCosine;
    }

    public void setA(final double x, final double y) {
        Point candidateA = new Point(x, y);
        if (!isTriangle(candidateA, pointB, pointC)) {
            throw new IllegalArgumentException("Triangle is formed by 3 points not on line ");
        }
        this.pointA.setX(x);
        this.pointA.setY(y);
    }

    public Point getA() {
        return pointA;
    }

    public void setB(final double x, final double y) {
        Point candidateB = new Point(x, y);
        if (!isTriangle(pointA, candidateB, pointC)) {
            throw new IllegalArgumentException("Triangle is formed by 3 points not on line ");
        }
        this.pointB.setX(x);
        this.pointB.setY(y);
    }

    public Point getB() {
        return pointB;
    }

    public void setC(final double x, final double y) {
        Point candidateC = new Point(x, y);
        if (!isTriangle(pointA, pointB, candidateC)) {
            throw new IllegalArgumentException("Triangle is formed by 3 points not on line ");
        }
        this.pointC.setX(x);
        this.pointC.setY(y);
    }

    public Point getC() {
        return pointC;
    }
}
