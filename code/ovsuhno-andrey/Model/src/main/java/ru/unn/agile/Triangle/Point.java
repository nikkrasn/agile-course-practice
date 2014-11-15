package ru.unn.agile.Triangle;

public class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int shift = 32;
        long temp = Double.doubleToLongBits(x);
        int result = (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(y);
        result = (shift - 1)  * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }

        Point certainPoint = (Point) object;
        return hashCode() == certainPoint.hashCode();
    }

    public double getDistance(final Point anotherPoint) {
        double result = (this.getX() - anotherPoint.getX()) * (this.getX() - anotherPoint.getX());
        result += (this.getY() - anotherPoint.getY()) * (this.getY() - anotherPoint.getY());
        result = Math.sqrt(result);
        return result;
    }

    public boolean isOnStraightLine(final Point pointA, final Point pointB) {
        if (pointA.equals(pointB)) {
            throw new IllegalArgumentException("Can't hold the line with only one point");
        }

        double xCoeff, yCoeff, freeCoeff;
        xCoeff = pointA.getY() - pointB.getY();
        yCoeff = pointB.getX() - pointA.getX();
        freeCoeff = xCoeff * pointA.getX() + yCoeff * pointA.getY();

        final double delta = 0.001;
        return Math.abs(xCoeff * this.getX() + yCoeff * this.getY() - freeCoeff) < delta;
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
