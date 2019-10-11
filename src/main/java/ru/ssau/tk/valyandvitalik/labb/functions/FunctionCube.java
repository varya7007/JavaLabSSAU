package ru.ssau.tk.valyandvitalik.labb.functions;

public class FunctionCube implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, 3);
    }
}
