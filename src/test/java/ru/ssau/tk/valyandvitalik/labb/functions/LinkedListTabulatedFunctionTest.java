package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private double[] x = {1.1, 2.2, 3.3, 4.4, 5.5};
    private double[] y = {2.1, 3.4, 4.7, 6., 7.3};
    private final double PRECISION = 0.1;
    private MathFunction testFunction = new FunctionCube();

    private LinkedListTabulatedFunction doList() {
        return new LinkedListTabulatedFunction(x, y);
    }

    private LinkedListTabulatedFunction doMathFunction() {
        return new LinkedListTabulatedFunction(testFunction, 5, 1, 10);
    }

    @Test
    public void testGetCount() {
        TabulatedFunction firstList;
        firstList = doList();
        assertEquals(firstList.getCount(), 5, PRECISION);
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(secondNum.getCount(), 10, PRECISION);
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction firstList = doList();
        assertEquals(firstList.leftBound(), 1.1, PRECISION);
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(secondNum.leftBound(), 1, PRECISION);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction firstList = doList();
        assertEquals(firstList.rightBound(), 5.5, PRECISION);
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(secondNum.rightBound(), 5, PRECISION);
    }

    @Test
    public void testGetX() {
        TabulatedFunction firstList = doList();
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.getX(0), 1.1, PRECISION);
        assertEquals(firstList.getX(2), 3.3, PRECISION);
    }

    @Test
    public void testGetY() {
        TabulatedFunction firstList = doList();
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.getY(0), 2.1, PRECISION);
        assertEquals(firstList.getY(2), 4.7, PRECISION);
    }

    @Test
    public void testSetY() {
        TabulatedFunction firstList = doList();
        firstList.setY(2, 22);
        TabulatedFunction secondNum = doMathFunction();
        secondNum.setY(3, 33);
        assertEquals(secondNum.getY(3), 33, PRECISION);
        assertEquals(firstList.getY(2), 22, PRECISION);
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction firstList = doList();
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.indexOfX(1.1), 0, PRECISION);
        assertEquals(firstList.indexOfX(-1), -1, PRECISION);
        assertEquals(secondNum.indexOfX(1), 0, PRECISION);
        assertEquals(secondNum.indexOfX(-1), -1, PRECISION);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction firstList = doList();
        TabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.indexOfY(2.1), 0, PRECISION);
        assertEquals(firstList.indexOfY(-1), -1, PRECISION);
        assertEquals(secondNum.indexOfY(1), 0, PRECISION);
        assertEquals(secondNum.indexOfY(-1), -1, PRECISION);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.floorIndexOfX(2.2), 1, PRECISION);
        assertEquals(firstList.floorIndexOfX(-1), 0, PRECISION);
        assertEquals(secondNum.floorIndexOfX(1), 0, PRECISION);
        assertEquals(secondNum.floorIndexOfX(-1), 0, PRECISION);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.extrapolateLeft(2.3), 3.5, PRECISION);
        assertEquals(secondNum.extrapolateLeft(1), 1, PRECISION);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.extrapolateRight(5.6), 7.4, PRECISION);
        assertEquals(secondNum.extrapolateRight(2), 22., PRECISION);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction doListTwo = new LinkedListTabulatedFunction(new double[]{12}, new double[]{13});
        });
        assertEquals(firstList.interpolate(6.2, 5), 8.1, PRECISION);
        assertEquals(secondNum.interpolate(3, 1), 21., PRECISION);
    }

    @Test
    public void testApply() {
        LinkedListTabulatedFunction firstList = doList();
        assertEquals(firstList.apply(3), 3.9, PRECISION);
        assertEquals(firstList.apply(3.3), 4.7, PRECISION);
    }

    @Test
    public void testIterator() {
        LinkedListTabulatedFunction firstList = doList();
        Iterator<Point> iterator = firstList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(doList().getX(i++), point.x, 0.0001);
        }
        ;
        i = 0;
        for (Point point : doList()) {
            assertEquals(doList().getX(i++), point.x, 0.0001);
        }
    }
}