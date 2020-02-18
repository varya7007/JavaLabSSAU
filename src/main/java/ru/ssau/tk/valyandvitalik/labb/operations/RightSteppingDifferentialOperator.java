package ru.ssau.tk.valyandvitalik.labb.operations;

import ru.ssau.tk.valyandvitalik.labb.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {
    RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                double rightDerivative;
                rightDerivative = (function.apply(x + step) - function.apply(x)) / step;
                return rightDerivative;
            }
        };
    }
}
