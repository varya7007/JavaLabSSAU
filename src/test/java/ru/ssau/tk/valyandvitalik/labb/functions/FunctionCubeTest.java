package ru.ssau.tk.valyandvitalik.labb.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FunctionCubeTest {
@Test
    public void testApplyCube(){
    FunctionCube testFunctionCube = new FunctionCube();
    assertEquals(Math.pow(3, 3), testFunctionCube.apply(3));
    assertEquals(Math.pow(-2, 3), testFunctionCube.apply(-2));
    assertEquals(Math.pow(0.2, 3), testFunctionCube.apply(0.2));
}
}
