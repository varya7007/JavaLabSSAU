package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.valyandvitalik.labb.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

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
}