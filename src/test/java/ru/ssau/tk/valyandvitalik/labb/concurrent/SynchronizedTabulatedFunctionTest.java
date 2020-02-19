package ru.ssau.tk.valyandvitalik.labb.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.valyandvitalik.labb.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.Point;

import java.util.Iterator;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1.1, 2.2, 3.3, 4.4, 5.5}, new double[]{0, 2, 4, 8, 16});
    SynchronizedTabulatedFunction syncList = new SynchronizedTabulatedFunction(function);

    @Test
    public void testGetCount() {
        assertEquals(syncList.getCount(), 5, 0.001);
    }

    @Test
    public void testGetX() {
        assertEquals(syncList.getX(1), 2.5, 0.001);
    }

    @Test
    public void testGetY() {
        assertEquals(syncList.getY(1), 8, 0.001);
    }

    @Test
    public void testSetY() {
        syncList.setY(1, 5);
        assertEquals(syncList.getY(1), 5, 0.001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(syncList.indexOfX(1.2), 0, 0.001);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(syncList.indexOfY(8), 1, 0.001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(syncList.leftBound(), 1.2, 0.001);
    }

    @Test
    public void testRightBound() {
        assertEquals(syncList.rightBound(), 9.5, 0.001);
    }

    @Test
    public void testIterator1() {
        Iterator<Point> iterator = function.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(syncList.getX(i++), point.x, 0.0001);
        }
        ;
        i = 0;
        for (Point point : syncList) {
            assertEquals(syncList.getX(i++), point.x, 0.0001);

        }
    }

    @Test
    public void testApply() {
        assertEquals(syncList.apply(2.5), 8, 0.001);
    }
}