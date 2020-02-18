package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.valyandvitalik.labb.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    double[] xValues = {1, 4, 9};
    double[] yValues = {1, 2, 3};
    double[] zValues = {0, 2, 4};
    LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(xValues,yValues);
    String newString = "LinkedListTabulatedFunction size =3\n[1.0; 1.0]\n[2.0; 4.0]\n[9.0; 3.0]";

    @Test
    public void testCheckLengthIsTheSame() {
        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] x = {1, 2, 3};
            double[] y = {1, 2};
        });
    }

    @Test
    public void testCheckSorted() {
    }

    @Test
    public void testTestToString() {
        assertEquals(newString, linkedList.toString());
    }
}