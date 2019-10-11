package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    @Test
    public void testCompositeFunction() {
        MathFunction sinFunc = new SinFunction();
        MathFunction cubeFunc = new FunctionCube();
        MathFunction compositeFunction = new CompositeFunction(sinFunc, cubeFunc);
        assertEquals(Math.pow(Math.sin(60),3), compositeFunction.apply(60));
    }
}
