package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    @Test
    public void testCompositeFunction() {
        MathFunction sinFunc = new FunctionSin();
        MathFunction cubeFunc = new FunctionCube();
        MathFunction compositeFunction = new CompositeFunction(sinFunc, cubeFunc);
        assertEquals(compositeFunction.apply(60), Math.pow(Math.sin(60), 3));
    }

    @Test
    public void testApplyCompositeFunction() {
        MathFunction testFunctionCube = new FunctionCube();
        FunctionSqrt testFunctionSqrt = new FunctionSqrt();
        assertEquals(testFunctionSqrt.andThen(testFunctionCube).apply(4), Math.sqrt(Math.pow(4, 3)));
    }
}

