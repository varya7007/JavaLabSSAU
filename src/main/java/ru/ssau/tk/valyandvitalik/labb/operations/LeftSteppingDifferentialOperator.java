package ru.ssau.tk.valyandvitalik.labb.operations;

import ru.ssau.tk.valyandvitalik.labb.functions.*;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                double leftDerivative;
                leftDerivative = (function.apply(x) - function.apply(x - step)) / step;
                return leftDerivative;
            }
        };
    }
}