package ru.ssau.tk.valyandvitalik.labb.functions.factory;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.valyandvitalik.labb.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.assertTrue;

public class LinkedListTabulatedFunctionFactoryTest {
    @Test
    public void testCreate()

    {
        LinkedListTabulatedFunctionFactory testClass = new LinkedListTabulatedFunctionFactory();
        Assert.assertSame(LinkedListTabulatedFunction.class, (testClass.create(new double[]{2.3, 3.1}, new double[]{2.3, 3.1})).getClass());
        assertTrue(testClass.create(new double[]{2.3, 3.1}, new double[]{2.3, 3.1}) instanceof LinkedListTabulatedFunction);
    }
}
