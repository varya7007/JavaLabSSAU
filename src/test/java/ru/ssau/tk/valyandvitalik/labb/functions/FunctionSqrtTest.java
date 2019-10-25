package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FunctionSqrtTest {

    @Test
    public void testApply() {
        FunctionSqrt testFunctionSqrt = new FunctionSqrt();
        assertEquals(Math.sqrt(4), testFunctionSqrt.apply(4));
    }
}