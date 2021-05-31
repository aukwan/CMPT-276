package ca.sfu.cmpt276.asn1.model;

/*
  JUnit5 class to test functions in DepthOfFieldCalculator
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepthOfFieldCalculatorTest {

    @Test
    void getHyperFocalDistance() {
        Lens testLens = new Lens("Canon", 1.8, 50);
        double expected;
        // Case 1:
        expected = 34.48;
        DepthOfFieldCalculator dofCalc1 = new DepthOfFieldCalculator(testLens, 20, 2.5);
        assertEquals(expected, dofCalc1.getHyperFocalDistance() / 1000, 0.005);
        // Case 2:
        expected = 45.37;
        DepthOfFieldCalculator dofCalc2 = new DepthOfFieldCalculator(testLens, 20, 1.9);
        assertEquals(expected, dofCalc2.getHyperFocalDistance() / 1000, 0.005);
        // Case 3:
        expected = 8.62;
        DepthOfFieldCalculator dofCalc3 = new DepthOfFieldCalculator(testLens, 20, 10);
        assertEquals(expected, dofCalc3.getHyperFocalDistance() / 1000, 0.005);
    }

    @Test
    void getNearFocalPoint() {
        Lens testLens = new Lens("Nikon", 4, 200);
        double expected;
        // Case 1:
        expected = 9.34;
        DepthOfFieldCalculator dofCalc1 = new DepthOfFieldCalculator(testLens, 10000, 10);
        assertEquals(expected, dofCalc1.getNearFocalPoint() / 1000, 0.005);
        // Case 2:
        expected = 22.46;
        DepthOfFieldCalculator dofCalc2 = new DepthOfFieldCalculator(testLens, 120000, 50);
        assertEquals(expected, dofCalc2.getNearFocalPoint() / 1000, 0.005);
        // Case 3:
        expected = 10.84;
        DepthOfFieldCalculator dofCalc3 = new DepthOfFieldCalculator(testLens, 50000, 100);
        assertEquals(expected, dofCalc3.getNearFocalPoint() / 1000, 0.005);
    }

    @Test
    void getFarFocalPoint() {
        Lens testLens = new Lens("Tamron", 2.8, 90);
        double expected;
        // Case 1:
        expected = 25.44;
        DepthOfFieldCalculator dofCalc1 = new DepthOfFieldCalculator(testLens, 20000, 3);
        assertEquals(expected, dofCalc1.getFarFocalPoint() / 1000, 0.005);
        // Case 2:
        expected = Double.POSITIVE_INFINITY;
        DepthOfFieldCalculator dofCalc2 = new DepthOfFieldCalculator(testLens, 123000, 50);
        assertEquals(expected, dofCalc2.getFarFocalPoint() / 1000, 0.005);
        // Case 3:
        expected = 5.26;
        DepthOfFieldCalculator dofCalc3 = new DepthOfFieldCalculator(testLens, 5000, 2.8);
        assertEquals(expected, dofCalc3.getFarFocalPoint() / 1000, 0.005);
    }

    @Test
    void getDepthOfField() {
        Lens testLens = new Lens("Sigma", 2.8, 200);
        double expected;
        // Case 1:
        expected = 42.25;
        DepthOfFieldCalculator dofCalc1 = new DepthOfFieldCalculator(testLens, 100000, 2.8);
        assertEquals(expected, dofCalc1.getDepthOfField() / 1000, 0.005);
        // Case 2:
        expected = 59.22;
        DepthOfFieldCalculator dofCalc2 = new DepthOfFieldCalculator(testLens, 20000, 50);
        assertEquals(expected, dofCalc2.getDepthOfField() / 1000, 0.005);
        // Case 3:
        expected = 0.24;
        DepthOfFieldCalculator dofCalc3 = new DepthOfFieldCalculator(testLens, 5000, 7);
        assertEquals(expected, dofCalc3.getDepthOfField() / 1000, 0.005);
    }
}