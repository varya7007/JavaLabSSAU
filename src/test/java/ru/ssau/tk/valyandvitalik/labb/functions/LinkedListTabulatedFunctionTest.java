package ru.ssau.tk.valyandvitalik.labb.functions;

import com.sun.org.apache.xpath.internal.functions.Function;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private double[] x = {1.1, 2.2, 3.3, 4.4, 5.5};
    private double[] y = {2.1, 3.4, 4.7, 9., 10.3};
    private double PRECISION = 0.1;
    private MathFunction testFunction = new FunctionCube();

    private LinkedListTabulatedFunction doList() {
        return new LinkedListTabulatedFunction(x, y);
    }

    private LinkedListTabulatedFunction doMathFunction() {
        return new LinkedListTabulatedFunction(testFunction, 1, 5, 10);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction firstList;
        firstList = doList();
        assertEquals(firstList.getCount(), 5, PRECISION);
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(secondNum.getCount(), 10, PRECISION);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction firstList = doList();
        assertEquals(firstList.leftBound(), 1.1, PRECISION);
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(secondNum.leftBound(), 1, PRECISION);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction firstList = doList();
        assertEquals(firstList.rightBound(), 5.5, PRECISION);
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(secondNum.rightBound(), 5, PRECISION);
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.getX(0), 1.1, PRECISION);
        assertEquals(firstList.getX(2), 3.3, PRECISION);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.getY(0), 2.1, PRECISION);
        assertEquals(firstList.getY(2), 4.7, PRECISION);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction firstList = doList();
        firstList.setY(2, 22);
        LinkedListTabulatedFunction secondNum = doMathFunction();
        secondNum.setY(3, 33);
        assertEquals(secondNum.getY(3), 33, PRECISION);
        assertEquals(firstList.getY(2), 22, PRECISION);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.indexOfX(1.1),0,PRECISION);
        assertEquals(firstList.indexOfX(-1),-1,PRECISION);
        assertEquals(secondNum.indexOfX(1),0,PRECISION);
        assertEquals(secondNum.indexOfX(-1),-1,PRECISION);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.indexOfY(2.1),0,PRECISION);
        assertEquals(firstList.indexOfY(-1),-1,PRECISION);
        assertEquals(secondNum.indexOfY(1),0,PRECISION);
        assertEquals(secondNum.indexOfY(-1),-1,PRECISION);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.floorIndexOfX(2.2),1,PRECISION);
        assertEquals(firstList.floorIndexOfX(-1),0,PRECISION);
        assertEquals(secondNum.floorIndexOfX(1),0,PRECISION);
        assertEquals(secondNum.floorIndexOfX(-1),0,PRECISION);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.extrapolateLeft(2.3),4.6,PRECISION);
        assertEquals(secondNum.extrapolateLeft(1),1,PRECISION);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.extrapolateRight(4.5),8.2,PRECISION);
        assertEquals(secondNum.extrapolateRight(2),22.,PRECISION);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction firstList = doList();
        LinkedListTabulatedFunction secondNum = doMathFunction();
        assertEquals(firstList.interpolate(6.2,5),8.1,PRECISION);
        assertEquals(secondNum.interpolate(3,1),21.,PRECISION);
    }
}