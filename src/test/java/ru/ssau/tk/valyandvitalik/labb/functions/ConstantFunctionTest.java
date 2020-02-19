package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    MathFunction mathFunction = new ConstantFunction(10);
@Test
public void testApply(){
    assertEquals(mathFunction.apply(1), 10, 0.0001);
    }
    @Test
    public void testGetConstant() {
assertEquals(((ConstantFunction) mathFunction).getConstant(),10,0.0001);
    }
}