package ru.ssau.tk.valyandvitalik.labb.operations;

import ru.ssau.tk.valyandvitalik.labb.functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                double middleDerivative;
                middleDerivative = (function.apply(x + step) - function.apply(x - step)) / (2 * step);
                return middleDerivative;
            }
        };
    }
}
