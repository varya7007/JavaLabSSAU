package ru.ssau.tk.valyandvitalik.labb.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.assertTrue;

public class TabulatedDifferentialOperatorTest {

    TabulatedDifferentialOperator isArray = new TabulatedDifferentialOperator();
    TabulatedDifferentialOperator linkedList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

    @Test
    public void testGetFactory() {
        assertTrue(linkedList.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        isArray.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(isArray.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testDerive() {
    }
}