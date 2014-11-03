package ru.unn.agile.Triangle;

public class Triangle {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    public Triangle(final double aX, final double aY,
                    final double bX, final double bY,
                    final double cX, final double cY) {
        Point pointA = new Point(aX, aY);
        Point pointB = new Point(bX, bY);
        Point pointC = new Point(cX, cY);
        if (!Point.isTriangle(pointA, pointB, pointC)) {
            throw new IllegalArgumentException("Triangle is formed by 3 points not on line ");
        }
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

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

    public double[] countAnglesCosine() {
        final int angleNum = 3;
        double[] anglesCosine = new double[angleNum];
        double[] lengths = countLengths();

        final double half = 0.5;

        anglesCosine[0] = lengths[0] * lengths[0];
        anglesCosine[0] += lengths[2] * lengths[2];
        anglesCosine[0] -= lengths[1] * lengths[1];
        anglesCosine[0] *= half;
        anglesCosine[0] /= lengths[0] * lengths[2];

        anglesCosine[1] = lengths[0] * lengths[0];
        anglesCosine[1] += lengths[1] * lengths[1];
        anglesCosine[1] -= lengths[2] * lengths[2];
        anglesCosine[1] *= half;
        anglesCosine[1] /= lengths[0] * lengths[1];

        anglesCosine[2] = lengths[1] * lengths[1];
        anglesCosine[2] += lengths[2] * lengths[2];
        anglesCosine[2] -= lengths[0] * lengths[0];
        anglesCosine[2] *= half;
        anglesCosine[2] /= lengths[1] * lengths[2];

        return anglesCosine;
    }

    public void setA(final double x, final double y) {
        Point candidateA = new Point(x, y);
        if (!Point.isTriangle(candidateA, pointB, pointC)) {
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
        if (!Point.isTriangle(pointA, candidateB, pointC)) {
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
        if (!Point.isTriangle(pointA, pointB, candidateC)) {
            throw new IllegalArgumentException("Triangle is formed by 3 points not on line ");
        }
        this.pointC.setX(x);
        this.pointC.setY(y);
    }

    public Point getC() {
        return pointC;
    }
}
