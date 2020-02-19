package ru.ssau.tk.valyandvitalik.labb.functions;

public class ConstantFunction implements MathFunction {
    final private double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    @Override
    public double apply(double x) {
        return constant;
    }

public double getConstant(){
        return constant;
    }
}
