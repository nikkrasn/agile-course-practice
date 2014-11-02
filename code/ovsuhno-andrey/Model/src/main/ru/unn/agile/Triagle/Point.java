package ru.unn.agile.Triangle;

public class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object object) {
        Point certainPoint = (Point) object;
        return certainPoint.getX() == getX() && certainPoint.getY() == getY();
    }

    public double getDistance(final Point anotherPoint) {
        double result = (this.getX() - anotherPoint.getX()) * (this.getX() - anotherPoint.getX());
        result += (this.getY() - anotherPoint.getY()) * (this.getY() - anotherPoint.getY());
        result = Math.sqrt(result);
        return result;
    }

    public boolean isOnStraightLine(final Point A, final Point B) {
        if (A.equals(B)) {
            throw new IllegalArgumentException();
        }

        double xCoeff, yCoeff, freeCoeff;
        xCoeff = A.getY() - B.getY();
        yCoeff = B.getX() - A.getX();
        freeCoeff = xCoeff * A.getX() + yCoeff * A.getY();

        return ((xCoeff * this.getX() + yCoeff * this.getY()) == freeCoeff);
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return StringFormatter.format(this);
    }
}