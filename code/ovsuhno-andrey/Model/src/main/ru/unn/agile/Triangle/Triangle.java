package ru.unn.agile.Triangle;

public class Triangle {
    private Point A;
    private Point B;
    private Point C;

    public Triangle(final double aX, final double aY,
                    final double bX, final double bY,
                    final double cX, final double cY) {
        Point A = new Point(aX, aY);
        Point B = new Point(bX, bY);
        Point C = new Point(cX, cY);
        if ((A.equals(B)) || (B.equals(C)) || (C.equals(A))) {
            throw new IllegalArgumentException();
        }
        else if (C.isOnStraightLine(A, B)) {
            throw new IllegalArgumentException();
        }
        else {
            this.A = A;
            this.B = B;
            this.C = C;
        }
    }

    public double[] countLengths() {
        double[] lengths = new double[3];
        lengths[0] = A.getDistance(B);
        lengths[1] = B.getDistance(C);
        lengths[2] = C.getDistance(A);

        return lengths;
    }

    public double countPerimeter() {
        double perimeter = A.getDistance(B);
        perimeter += B.getDistance(C);
        perimeter += C.getDistance(A);

        return perimeter;
    }

    public double countSpace() {
        double halfPerimeter = 0.5 * countPerimeter();
        double[] lengths = countLengths();

        double space = halfPerimeter;
        space *= halfPerimeter - lengths[0];
        space *= halfPerimeter - lengths[1];
        space *= halfPerimeter - lengths[2];
        space = Math.sqrt(space);

        return space;
    }

    public double[] countAnglesCosine() {
        double[] anglesCosine = new double[3];
        double[] lengths = countLengths();

        anglesCosine[0] = 0.5 * ((lengths[0] * lengths[0]) + (lengths[2] * lengths[2]) - (lengths[1] * lengths[1]));
        anglesCosine[0] /= lengths[0] * lengths[2];
        anglesCosine[1] = 0.5 * ((lengths[0] * lengths[0]) + (lengths[1] * lengths[1]) - (lengths[2] * lengths[2]));
        anglesCosine[1] /= lengths[0] * lengths[1];
        anglesCosine[2] = 0.5 * ((lengths[1] * lengths[1]) + (lengths[2] * lengths[2]) - (lengths[0] * lengths[0]));
        anglesCosine[2] /= lengths[1] * lengths[2];

        return anglesCosine;
    }

    public void setA(final double x, final double y) {
        this.A.setX(x);
        this.A.setY(y);
    }

    public Point getA() {
        return A;
    }

    public void setB(final double x, final double y) {
        this.B.setX(x);
        this.B.setY(y);
    }

    public Point getB() {
        return B;
    }

    public void setC(final double x, final double y) {
        this.C.setX(x);
        this.C.setY(y);
    }

    public Point getC() {
        return C;
    }
}
