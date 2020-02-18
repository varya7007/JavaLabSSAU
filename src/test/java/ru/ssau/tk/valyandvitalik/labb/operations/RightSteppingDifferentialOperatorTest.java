package ru.ssau.tk.valyandvitalik.labb.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.valyandvitalik.labb.functions.FunctionCube;
import ru.ssau.tk.valyandvitalik.labb.functions.FunctionSin;
import ru.ssau.tk.valyandvitalik.labb.functions.FunctionSqrt;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 5;
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new FunctionCube()).apply(4), (Math.pow(4 + step, 3) - Math.pow((4), 3)) / step);
        assertEquals(differentialOperator.derive(new FunctionSin()).apply(45), (Math.sin(45 + step) - Math.sin(45)) / step);
        assertEquals(differentialOperator.derive(new FunctionSqrt()).apply(144), (Math.sqrt(144 + step) - Math.sqrt(144)) / step);
    }
}