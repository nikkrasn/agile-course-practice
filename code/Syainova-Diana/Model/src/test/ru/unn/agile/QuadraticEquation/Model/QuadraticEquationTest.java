package ru.unn.agile.QuadraticEquation.Model;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class QuadraticEquationTest {
    private final double delta = 0.001;

    @Test
    public void canCreateQuadraticEquationWithPositiveCoefficients() {
        QuadraticEquation equation = new QuadraticEquation(1, 1, 1);
        assertNotNull(equation);
    }

    @Test
    public void canCreateQuadraticEquationWithNegativeCoefficients() {
        QuadraticEquation equation = new QuadraticEquation(-2, -2, -2);
        assertNotNull(equation);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotCreateQuadraticEquationWithZeroFirstCoefficient() {
        QuadraticEquation equation = new QuadraticEquation(0, 1, 1);
    }

    @Test
    public void canGetZeroDiscriminant() {
        QuadraticEquation equation = new QuadraticEquation(1, 2, 1);
        double discriminant = equation.getDiscriminant();
        assertEquals(0.0, discriminant, delta);
    }

    @Test
    public void canGetPositiveDiscriminant() {
        QuadraticEquation equation = new QuadraticEquation(1, 3, 0);
        double discriminant = equation.getDiscriminant();
        assertEquals(9.0, discriminant, delta);
    }

    @Test
    public void canGetNegativeDiscriminant() {
        QuadraticEquation equation = new QuadraticEquation(1, 1, 1);
        double discriminant = equation.getDiscriminant();
        assertEquals(-3.0, discriminant, delta);
    }

    @Test
    public void canGetFirstRootWithPositiveDiscriminat() {
        QuadraticEquation equation = new QuadraticEquation(1, 2, -3);
        double root = equation.getFirstRoot();
        assertEquals(1.0, root, delta);
    }

    @Test
    public void canGetFirstRootWithZeroDiscriminat() {
        QuadraticEquation equation = new QuadraticEquation(1, 2, 1);
        double root = equation.getFirstRoot();
        assertEquals(-1.0, root, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetFirstRootWithNegativeDiscriminat() {
        QuadraticEquation equation = new QuadraticEquation(1, 1, 1);
        double root = equation.getFirstRoot();
    }

    @Test
    public void canGetSecondRootWithPositiveDiscriminat() {
        QuadraticEquation equation = new QuadraticEquation(1, 2, -3);
        double root = equation.getSecondRoot();
        assertEquals(-3.0, root, delta);
    }

    @Test
    public void canGetSecondRootWithZeroDiscriminat() {
        QuadraticEquation equation = new QuadraticEquation(1, 2, 1);
        double root = equation.getSecondRoot();
        assertEquals(-1.0, root, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetSecondRootWithNegativeDiscriminat() {
        QuadraticEquation equation = new QuadraticEquation(1, 1, 1);
        double root = equation.getSecondRoot();
    }

    @Test
    public void canGetTwoRootsWithPositiveDiscriminant() {
        QuadraticEquation equation = new QuadraticEquation(4, -3, -1);
        double [] roots = equation.getTwoRoots();
        double [] correctRoots = {1.0, -0.25};
        assertArrayEquals(correctRoots, roots, delta);
    }

    @Test
    public void canGetTwoRootsWithZeroDiscriminant() {
        QuadraticEquation equation = new QuadraticEquation(4, 4, 1);
        double [] roots = equation.getTwoRoots();
        double [] correctRoots = {-0.5, -0.5};
        assertArrayEquals(correctRoots, roots, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetTwoRootsWithNegativeDiscriminant() {
        QuadraticEquation equation = new QuadraticEquation(4, 2, 1);
        double[] roots = equation.getTwoRoots();
    }
  }
