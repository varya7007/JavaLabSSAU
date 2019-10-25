package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FunctionSinTest {

    @Test
    public void testApply() {
        FunctionSin testFunctionSin = new FunctionSin();
        assertEquals(Math.sin(3), testFunctionSin.apply(3));
    }
}